package com.taptech.ttis.controller;

import com.taptech.ttis.dto.MovieDTO;
import com.taptech.ttis.dto.UserMovieDTO;
import com.taptech.ttis.service.MovieRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tap on 7/30/16.
 */
@RestController
@RequestMapping("/api/movie")
public class MovieStatsController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    MovieRatingService movieRatingService;

    public static final String NO_MOVIES_FOUND = "No movies found for movieId=";

    @RequestMapping(value = "/watched/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> watchedMovies(@PathVariable String userId) throws Exception {
        logger.debug("watchedMovies({})", new Object[] {userId});
        ResponseEntity<?> responseEntity = null;
        UserMovieDTO userMovieDTO = movieRatingService.watchedMovies(userId);
        if (null == userMovieDTO || null == userMovieDTO.getMoviesWatched() || userMovieDTO.getMoviesWatched().size() <= 0){
            StringBuilder message = new StringBuilder(NO_MOVIES_FOUND);
            message.append(userMovieDTO);
            responseEntity = new ResponseEntity<String>(message.toString(), HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(userMovieDTO , HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/{movieId}/averageRating", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> averageRating(@PathVariable String movieId) throws Exception {
        logger.debug("averageRating({})", new Object[] {movieId});
        ResponseEntity<?> responseEntity = null;
        Double rating = movieRatingService.averageRating(movieId);
        if (null == rating){
            StringBuilder message = new StringBuilder("No rating found for movieId=");
            message.append(movieId);
            responseEntity = new ResponseEntity<String>(message.toString(), HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(rating.toString() , HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/top/{genre}/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> topMovies(@PathVariable String genre, @PathVariable String userId) throws Exception {
        logger.debug("topMovies({})", new Object[] {genre, userId});
        ResponseEntity<?> responseEntity = null;
        List<MovieDTO> movieDTOs = (List<MovieDTO>)movieRatingService.findTopMovies(genre, userId);
        if (null == movieDTOs || movieDTOs.size() <= 0){
            StringBuilder message = new StringBuilder("No movies found for userId=");
            message.append(userId);
            message.append("");
            responseEntity = new ResponseEntity<String>(message.toString(), HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(movieDTOs , HttpStatus.OK);
        }
        return responseEntity;
    }
}
