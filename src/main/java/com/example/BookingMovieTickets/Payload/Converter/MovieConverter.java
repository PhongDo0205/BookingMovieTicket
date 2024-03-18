package com.example.BookingMovieTickets.Payload.Converter;

import com.example.BookingMovieTickets.Entity.Movie;
import com.example.BookingMovieTickets.Payload.DTO.MovieDTO;
import com.example.BookingMovieTickets.Payload.Request.Movie.AddMovieRequest;
import com.example.BookingMovieTickets.Payload.Request.Movie.UpdateMovieRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class MovieConverter {
    public MovieDTO entityToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setMovieDuration(movie.getMovieDuration());
        movieDTO.setEndTime(movie.getEndTime());
        movieDTO.setPremiereDate(movie.getPremiereDate());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setImage(movie.getImage());
        movieDTO.setLanguage(movie.getLanguage());
        movieDTO.setMovieTypeId(movie.getMovieTypeId());
        movieDTO.setRateId(movie.getRateId());
        movieDTO.setName(movie.getName());
        movieDTO.setTrailer(movie.getTrailer());
        movieDTO.setActive(movie.isActive());
        movieDTO.setHeroImage(movie.getHeroImage());
        return movieDTO;
    }


    public List<MovieDTO> entityToDTOList(List<Movie> movieList){
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie movie:movieList){
            assert false;
            movieDTOList.add(entityToDTO(movie));
        }
        return movieDTOList;
    }

    public Movie addMovieFromRequest(AddMovieRequest request) {
        Movie movie = new Movie();
        movie.setMovieDuration(request.getMovieDuration());
        movie.setEndTime(request.getEndTime());
        movie.setPremiereDate(request.getPremiereDate());
        movie.setDescription(request.getDescription());
        movie.setDirector(request.getDirector());
        movie.setImage(request.getImage());
        movie.setLanguage(request.getLanguage());
        movie.setMovieTypeId(request.getMovieTypeId());
        movie.setName(request.getName());
        movie.setRateId(request.getRateId());
        movie.setTrailer(request.getTrailer());
        movie.setActive(request.isActive());
        movie.setHeroImage(request.getHeroImage());
        return movie;
    }

    public Movie updateMovieFromRequest(UpdateMovieRequest request, Movie existingMovie) {
        if (request.getId() == 0) {
            return null;
        } else {
            existingMovie.setId(request.getId());
        }
        if (request.getMovieDuration() > 0) {
            existingMovie.setMovieDuration(request.getMovieDuration());
        }
        if (request.getEndTime() != null) {
            existingMovie.setEndTime(request.getEndTime());
        }
        if (request.getPremiereDate() != null) {
            existingMovie.setPremiereDate(request.getPremiereDate());
        }
        if (request.getDescription() != null) {
            existingMovie.setDescription(request.getDescription());
        }
        if (request.getDirector() != null) {
            existingMovie.setDirector(request.getDirector());
        }
        if (request.getImage() != null) {
            existingMovie.setImage(request.getImage());
        }
        if (request.getLanguage() != null) {
            existingMovie.setLanguage(request.getLanguage());
        }
        if (request.getMovieTypeId() > 0) {
            existingMovie.setMovieTypeId(request.getMovieTypeId());
        }
        if (request.getName() != null) {
            existingMovie.setName(request.getName());
        }
        if (request.getRateId() > 0) {
            existingMovie.setRateId(request.getRateId());
        }
        if (request.getTrailer() != null) {
            existingMovie.setTrailer(request.getTrailer());
        }
        existingMovie.setActive(request.isActive());
        if (request.getHeroImage() != null) {
            existingMovie.setHeroImage(request.getHeroImage());
        }
        return existingMovie;
    }

}
