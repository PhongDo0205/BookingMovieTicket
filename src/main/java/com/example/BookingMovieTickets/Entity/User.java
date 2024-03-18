package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int point;

    private String email;

    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    private String password;

    @ManyToOne
    @JoinColumn(name = "rankCustomerId", insertable = false, updatable = false)
    @JsonBackReference
    private RankCustomer rankCustomerofUser;
    @Column(name = "rankCustomerId")
    private int rankCustomerId;

    @ManyToOne
    @JoinColumn(name = "userStatusId", insertable = false, updatable = false)
    @JsonBackReference
    private UserStatus userStatusofUser;
    @Column(name = "userStatusId")
    private int userStatusId;

    @Column(name = "isActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    @JsonBackReference
    private Role roleofUser;
    @Column(name = "roleId")
    private int roleId;

    @OneToMany(mappedBy = "userBill")
    @JsonManagedReference
    private Set<Bill> Bills;

    @OneToMany(mappedBy = "userfromConfirmEmail")
    @JsonManagedReference
    private Set<ConfirmEmail> confirmEmails;

    @OneToMany(mappedBy = "userfromRefreshToken")
    @JsonManagedReference
    private Set<RefreshToken> refreshTokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleofUser.getCode().toString()));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
