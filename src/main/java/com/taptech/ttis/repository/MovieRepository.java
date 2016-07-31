package com.taptech.ttis.repository;

import com.taptech.ttis.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tap on 7/30/16.
 */
@Repository("movieRepository")
public interface MovieRepository extends CrudRepository<Movie, String> {
}
