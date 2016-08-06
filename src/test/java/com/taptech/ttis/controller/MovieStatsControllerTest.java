package com.taptech.ttis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taptech.ttis.TestConfiguration;
import com.taptech.ttis.dto.MovieDTO;
import com.taptech.ttis.dto.UserMovieDTO;
import com.taptech.ttis.service.MovieRatingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by tap on 7/31/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfiguration.class, loader=AnnotationConfigContextLoader.class)
@ActiveProfiles({"test"})
public class MovieStatsControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(MovieStatsControllerTest.class);
    @InjectMocks
    MovieStatsController movieStatsController;

    @Mock
    MovieRatingService movieRatingService;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    private static final String USER_ID_EMPTY = "";
    private static final String USER_ID_NOT_EMPTY = "123456";
    private static final String MOVIE_NAME = "Star Wars";
    private static final Double MOVIE_RATING = 8.5d;

    private static final Integer ZERO = new Integer(0);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testWatchedMovies_userIdEmpty() throws Exception {
        //Arrange
        String msg = MovieStatsController.NO_MOVIES_FOUND;
        when(movieRatingService.watchedMovies(anyString())).thenReturn(getUserIdEmptyUserDTO(USER_ID_EMPTY));
        //Act
        ResponseEntity<?> responseEntity = movieStatsController.watchedMovies(USER_ID_EMPTY);
        //Assert
        assertNotNull("Response should not be null",responseEntity);
        String responseString = (String)responseEntity.getBody();

        assertTrue("Should return 404", responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assertTrue("Should contain String "+msg, responseString.contains(msg));
    }

    @Test
    public void testWatchedMovies_withMovieLst() throws Exception {
        //Arrange
        when(movieRatingService.watchedMovies(anyString())).thenReturn(getUserIdUserDTO(USER_ID_NOT_EMPTY));
        //Act
        ResponseEntity<?> responseEntity = movieStatsController.watchedMovies(USER_ID_NOT_EMPTY);
        //Assert
        assertNotNull("Response should not be null",responseEntity);

        logger.info("");
        assertTrue("Should return 200", responseEntity.getStatusCode().equals(HttpStatus.OK));
        assertNotNull("Response should not be null",responseEntity);
        UserMovieDTO userMovieDTO = (UserMovieDTO)responseEntity.getBody();
        assertNotNull("UserMovieDTO should not be null",userMovieDTO);
        assertFalse("Movie List should not be empty", userMovieDTO.getMoviesWatched().isEmpty());
    }

    private UserMovieDTO getUserIdUserDTO(String userId) {
        UserMovieDTO userMovieDTO = new UserMovieDTO();
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setGenre(UUID.randomUUID().toString());
        movieDTO.setMovieId(UUID.randomUUID().toString());
        movieDTO.setMovieName(MOVIE_NAME);
        movieDTO.setRating(MOVIE_RATING);
        userMovieDTO.setMoviesWatched(Collections.singleton(movieDTO));
        userMovieDTO.setTotalCount(1);
        userMovieDTO.setUserId(userId);
        return new UserMovieDTO();
    }


    private UserMovieDTO getUserIdEmptyUserDTO(String userId) {
        UserMovieDTO userMovieDTO = new UserMovieDTO();
        userMovieDTO.setMoviesWatched(Collections.EMPTY_LIST);
        userMovieDTO.setTotalCount(0);
        userMovieDTO.setUserId(userId);
        return new UserMovieDTO();
    }
}
