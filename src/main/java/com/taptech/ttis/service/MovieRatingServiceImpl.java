package com.taptech.ttis.service;

import com.taptech.ttis.dto.MovieDTO;
import com.taptech.ttis.dto.UserMovieDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * Created by tap on 7/30/16.
 */
@Service("movieRatingService")
public class MovieRatingServiceImpl implements MovieRatingService {

    private static final Logger logger = LoggerFactory.getLogger(MovieRatingServiceImpl.class);

    @Autowired
    EntityManager entityManager;

    @Override
    public Collection<MovieDTO> findUserMovies(String userId) {
        return null;
    }

    @Override
    public Collection<MovieDTO> findTopMovies(String genre, String userId) {
        return null;
    }

    @Override
    public Float averageRating(String movieId) {
        return null;
    }

    @Override
    public UserMovieDTO watchedMovies(String userId) {
        return null;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
