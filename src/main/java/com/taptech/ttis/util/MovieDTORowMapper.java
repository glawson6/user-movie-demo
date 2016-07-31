package com.taptech.ttis.util;

import com.taptech.ttis.dto.MovieDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tap on 7/31/16.
 */
@Component
public class MovieDTORowMapper implements RowMapper, Serializable {

    @Override
    public Object mapRow(ResultSet resultSet, int index) throws SQLException {
        Object[] resultObject = new Object[4];
        resultObject[0] = resultSet.getString(1);
        resultObject[1] = resultSet.getString(2);
        resultObject[2] = resultSet.getDouble(3);
        resultObject[3] = resultSet.getString(4);
        return convertToMovieDTO(resultObject);
    }

    public MovieDTO convertToMovieDTO(Object[] result) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(result[0].toString());
        movieDTO.setMovieName(result[1].toString());
        movieDTO.setRating(((Double)result[2]).doubleValue());
        movieDTO.setGenre(result[3].toString());
        return movieDTO;
    }

}
