package com.taptech.ttis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tap on 7/30/16.
 */
@RestController
@RequestMapping("/api/movie")
public class MovieStatsController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/watched/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> watchedMovies(@PathVariable String userId) throws Exception {
        logger.debug("watchedMovies({})", new Object[] {userId});
        return new ResponseEntity<>("OK" , HttpStatus.OK);
    }

    @RequestMapping(value = "/averageRating/{movieId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> averageRating(@PathVariable String movieId) throws Exception {
        logger.debug("averageRating({})", new Object[] {movieId});
        return new ResponseEntity<>("OK" , HttpStatus.OK);
    }

    @RequestMapping(value = "/top/{genre}/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> topMovies(@PathVariable String genre, @PathVariable String userId) throws Exception {
        logger.debug("topMovies({})", new Object[] {genre, userId});
        return new ResponseEntity<>("OK" , HttpStatus.OK);
    }
}
