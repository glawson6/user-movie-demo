package com.taptech.ttis.repository;

import com.taptech.ttis.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tap on 7/30/16.
 */
@Repository("movie")
public interface MovieRepository extends CrudRepository<Movie, String> {

    @Query("SELECT m FROM Movie m where m.externalid = :externalid")
    Movie findByExternalid(@Param("externalid") int externalid);
}
