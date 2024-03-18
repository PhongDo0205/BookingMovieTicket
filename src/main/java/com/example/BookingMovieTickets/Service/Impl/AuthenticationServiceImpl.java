package com.example.BookingMovieTickets.Service.Impl;

import com.example.BookingMovieTickets.Config.Jwt.JwtUtils;
import com.example.BookingMovieTickets.Entity.ConfirmEmail;
import com.example.BookingMovieTickets.Entity.User;
import com.example.BookingMovieTickets.Payload.Converter.ConfirmEmailConverter;
import com.example.BookingMovieTickets.Payload.Converter.RefreshTokenConverter;
import com.example.BookingMovieTickets.Payload.Converter.UserConverter;
import com.example.BookingMovieTickets.Payload.DTO.ConfirmEmailDTO;
import com.example.BookingMovieTickets.Payload.DTO.UserDTO;
import com.example.BookingMovieTickets.Payload.Request.User.CreateUserRequest;
import com.example.BookingMovieTickets.Payload.Request.User.LoginRequest;
import com.example.BookingMovieTickets.Payload.Respond.JwtAuthenticationResponse;
import com.example.BookingMovieTickets.Payload.Respond.ResponseObject;
import com.example.BookingMovieTickets.Repository.ConfirmEmailRepo;
import com.example.BookingMovieTickets.Repository.RefreshTokenRepo;
import com.example.BookingMovieTickets.Repository.RoleRepo;
import com.example.BookingMovieTickets.Repository.UserRepo;
import com.example.BookingMovieTickets.Service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Autowired
    private ConfirmEmailRepo confirmEmailRepo;

    @Autowired
    private UserConverter usersConverter;
    @Autowired
    private RefreshTokenConverter refreshTokenConverter;
    @Autowired

    private ConfirmEmailConverter confirmEmailConverter;

    @Autowired
    private ResponseObject<UserDTO> userDTOResponseObject;
//    private ResponseObject<RefreshTokenDTO> refreshTokenDTOResponseObject;
    @Autowired
    private ResponseObject<ConfirmEmailDTO> confirmEmailDTOResponseObject;
//    private ResponseObject<String> stringResponseObject;
//    private ResponseObject<JwtResponseDTO> jwtResponseDTOResponseObject;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;


    @Override
    public ResponseObject<UserDTO> signup(CreateUserRequest request) {
        boolean isUsernameExists = userRepo.existsByName(request.getName());
        if (isUsernameExists) {
            return userDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Error: Username is already taken!", null);
        }

        if (userRepo.findByName(request.getEmail()).isPresent()) {
            return userDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Error: Email already exists!", null);
        }
        var newUser = usersConverter.createNewUser(request);
        userRepo.save(newUser);

        var userdto = usersConverter.entityToDTO(newUser);
        sendConfirmationEmail(userdto);
        return userDTOResponseObject.responseSuccess("User registered successfully!", userdto);
    }


    @Override
    public void sendConfirmationEmail(UserDTO userDTO) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiredTime = LocalDateTime.now().plusMinutes(60);
        var newToken = refreshTokenConverter.generateNewToken(token, expiredTime, userDTO.getId());
        refreshTokenRepo.save(newToken);

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(userDTO.getEmail());
            helper.setSubject("Confirmation Email");
            helper.setText("Your confirmation code is: " + token);

            javaMailSender.send(message);
            var newConfirmEmail = confirmEmailConverter.confirmEmail(userDTO.getId(), token);
            confirmEmailRepo.save(newConfirmEmail);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send confirmation email");
        }
    }

    @Override
    public JwtAuthenticationResponse signin(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().toString();

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles)).getBody();
    }
    @Override
    public ResponseObject<UserDTO> forgotPassword(String username) {
        if (username == null) {
            return userDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Please enter your username", null);
        }

        Optional<User> userOptional = userRepo.findByName(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            String token = UUID.randomUUID().toString();
            LocalDateTime expiredTime = LocalDateTime.now().plusMinutes(60);
            var newToken = refreshTokenConverter.generateNewToken(token, expiredTime, user.getId());
            refreshTokenRepo.save(newToken);

            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setTo(user.getEmail());
                helper.setSubject("Password Reset");
                helper.setText("Your password reset code is: " + token);

                javaMailSender.send(message);

                var newConfirmEmail = confirmEmailConverter.confirmEmail(user.getId(), token);
                confirmEmailRepo.save(newConfirmEmail);

                return userDTOResponseObject.responseSuccess("Password reset code sent successfully", null);
            } catch (MessagingException e) {
                throw new RuntimeException("Failed to send password reset code email");
            }
        } else {
            return userDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Cannot find the user with the provided username", null);
        }
    }

    @Override
    public ResponseObject<UserDTO> changePassword(String username, String confirmCode, String newPassword) {
        if (username == null || newPassword == null || confirmCode == null) {
            return userDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Invalid input", null);
        }

        ConfirmEmail confirmEmail = confirmEmailRepo.findByConfirmCode(confirmCode);
        if (confirmEmail != null) {
            if (confirmEmail.getRequiredDateTime().isAfter(LocalDateTime.now().minusMinutes(15))) {
                User user = userRepo.findByName(username).orElse(null);
                if (user != null) {
                    user.setPassword(bCryptPasswordEncoder.encode(newPassword));
                    userRepo.save(user);

                    confirmEmail.setConfirm(true);
                    confirmEmail.setExquiredDateTime(LocalDateTime.now());
                    confirmEmailRepo.save(confirmEmail);

                    return userDTOResponseObject.responseSuccess("Password changed successfully", usersConverter.entityToDTO(user));
                } else {
                    return userDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "User not found", null);
                }
            } else {
                return userDTOResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Confirmation code has expired", null);
            }
        } else {
            return userDTOResponseObject.responseError(HttpURLConnection.HTTP_NOT_FOUND, "Confirmation email not found or ConfirmCode is wrong", null);
        }
    }
}
