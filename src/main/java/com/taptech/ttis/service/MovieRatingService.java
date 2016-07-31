package com.taptech.ttis.service;

import com.taptech.ttis.dto.MovieDTO;
import com.taptech.ttis.dto.UserMovieDTO;

import java.util.Collection;

/**
 * Created by tap on 7/30/16.
 */
public interface MovieRatingService {

    Collection<MovieDTO> findUserMovies(String userId);
    Collection<MovieDTO> findTopMovies(String genre, String userId);
    Double averageRating(String movieId);
    UserMovieDTO watchedMovies(String userId);
}
