package com.taptech.ttis.dto;

import java.util.Collection;
import java.util.List;

/**
 * Created by tap on 7/30/16.
 */
public class UserMovieDTO {

    private String userId;
    private Integer totalCount;
    private Collection<MovieDTO> moviesWatched;

    public Collection<MovieDTO> getMoviesWatched() {
        return moviesWatched;
    }

    public void setMoviesWatched(Collection<MovieDTO> moviesWatched) {
        this.moviesWatched = moviesWatched;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserMovieDTO{");
        sb.append("moviesWatched=").append(moviesWatched);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", totalCount=").append(totalCount);
        sb.append('}');
        return sb.toString();
    }
}
