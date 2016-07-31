package com.taptech.ttis.repository;

import com.taptech.ttis.entity.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tap on 7/30/16.
 */
@Repository("genreRepository")
public interface GenreRepository extends CrudRepository<Genre, String> {
}
