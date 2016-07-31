package com.taptech.ttis.repository;

import com.taptech.ttis.entity.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tap on 7/30/16.
 */
@Repository("genre")
public interface GenreRepository extends CrudRepository<Genre, String> {

    @Query("SELECT g FROM Genre g where g.externalid = :externalid")
    Genre findByExternalid(@Param("externalid") int externalid);
}
