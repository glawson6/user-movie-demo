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
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;
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
    private static final String MOVIE_ID = UUID.randomUUID().toString();
    private static final String MOVIE_NAME = "Star Wars";
    private static final Double MOVIE_RATING = 8.5d;
    private static final String GENRE = "Adventure";
    private static final Integer ZERO = new Integer(0);
    private ObjectMapper objectMapper = new ObjectMapper();

    //Arrange
    //Act
    //Assert

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
        assertTrue("Should return 200", responseEntity.getStatusCode().equals(HttpStatus.OK));
        UserMovieDTO userMovieDTO = (UserMovieDTO)responseEntity.getBody();
        assertNotNull("Response should not be null",responseEntity);
        assertNotNull("UserMovieDTO should not be null",userMovieDTO);
        assertFalse("Movie List should not be empty", userMovieDTO.getMoviesWatched().isEmpty());
    }

    @Test
    public void testAverageRating_NoRatingsFound() throws Exception {
        //Arrange
        String msg = MovieStatsController.NO_RATINGS_FOUND;
        when(movieRatingService.averageRating(anyString())).thenReturn(null);
        //Act
        ResponseEntity<?> responseEntity = movieStatsController.averageRating(MOVIE_ID);
        //Assert
        assertNotNull("Response should not be null",responseEntity);
        String responseString = (String)responseEntity.getBody();
        assertTrue("Should return 404", responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assertTrue("Should contain String "+msg, responseString.contains(msg));
    }

    @Test
    public void testAverageRating_RatingsFound() throws Exception {
        //Arrange
        when(movieRatingService.averageRating(anyString())).thenReturn(MOVIE_RATING);
        //Act
        ResponseEntity<?> responseEntity = movieStatsController.averageRating(MOVIE_ID);
        //Assert
        assertNotNull("Response should not be null",responseEntity);
        String responseString = (String)responseEntity.getBody();
        assertTrue("Should return 404", responseEntity.getStatusCode().equals(HttpStatus.OK));
        assertEquals("Ratings should be equal", MOVIE_RATING.toString(),responseString);
    }

    @Test
    public void testTopMovies_MoviesFoundNull() throws Exception {
        //Arrange
        String msg = MovieStatsController.NO_USER_MOVIES_FOUND;
        when(movieRatingService.findTopMovies(anyString(), anyString())).thenReturn(null);
        //Act
        ResponseEntity<?> responseEntity = movieStatsController.topMovies(GENRE, USER_ID_NOT_EMPTY);
        //Assert
        assertNotNull("Response should not be null",responseEntity);
        String responseString = (String)responseEntity.getBody();
        assertTrue("Should return 404", responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assertTrue("Should contain String "+msg, responseString.contains(msg));
    }

    @Test
    public void testTopMovies_MoviesFoundEmptyList() throws Exception {
        //Arrange
        String msg = MovieStatsController.NO_USER_MOVIES_FOUND;
        when(movieRatingService.findTopMovies(anyString(), anyString())).thenReturn(Collections.emptyList());
        //Act
        ResponseEntity<?> responseEntity = movieStatsController.topMovies(GENRE, USER_ID_NOT_EMPTY);
        //Assert
        assertNotNull("Response should not be null",responseEntity);
        String responseString = (String)responseEntity.getBody();
        assertTrue("Should return 404", responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assertTrue("Should contain String "+msg, responseString.contains(msg));
    }

    @Test
    public void testTopMovies_MoviesFound() throws Exception {
        //Arrange
        String msg = MovieStatsController.NO_USER_MOVIES_FOUND;
        when(movieRatingService.findTopMovies(anyString(), anyString())).thenReturn(getMovieList());
        //Act
        ResponseEntity<?> responseEntity = movieStatsController.topMovies(GENRE, USER_ID_NOT_EMPTY);
        //Assert
        assertNotNull("Response should not be null",responseEntity);
        assertTrue("Should return 200", responseEntity.getStatusCode().equals(HttpStatus.OK));
        List<MovieDTO> movieDTOs = (List<MovieDTO>)responseEntity.getBody();
        assertNotNull("Response should not be null",responseEntity);
        assertNotNull("UserMovieDTO should not be null",movieDTOs);
        assertFalse("Movie List should not be empty", movieDTOs.isEmpty());
    }

    private UserMovieDTO getUserIdUserDTO(String userId) {
        UserMovieDTO userMovieDTO = new UserMovieDTO();
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setGenre(UUID.randomUUID().toString());
        movieDTO.setMovieId(UUID.randomUUID().toString());
        movieDTO.setMovieName(MOVIE_NAME);
        movieDTO.setRating(MOVIE_RATING);
        userMovieDTO.setMoviesWatched(Collections.singletonList(movieDTO));
        userMovieDTO.setTotalCount(userMovieDTO.getMoviesWatched().size());
        userMovieDTO.setUserId(userId);
        return userMovieDTO;
    }

    private List<MovieDTO> getMovieList(){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setGenre(UUID.randomUUID().toString());
        movieDTO.setMovieId(UUID.randomUUID().toString());
        movieDTO.setMovieName(MOVIE_NAME);
        movieDTO.setRating(MOVIE_RATING);
        return Collections.singletonList(movieDTO);
    }

    private UserMovieDTO getUserIdEmptyUserDTO(String userId) {
        UserMovieDTO userMovieDTO = new UserMovieDTO();
        userMovieDTO.setMoviesWatched(Collections.EMPTY_LIST);
        userMovieDTO.setTotalCount(userMovieDTO.getMoviesWatched().size());
        userMovieDTO.setUserId(userId);
        return userMovieDTO;
    }
}
