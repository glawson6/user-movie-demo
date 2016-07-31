package com.taptech.ttis.repository;

import com.taptech.ttis.entity.Movie;
import com.taptech.ttis.entity.MovieRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tap on 7/30/16.
 */
@Repository("movieRatingRepository")
public interface MovieRatingRepository extends CrudRepository<MovieRating, String> {
}
