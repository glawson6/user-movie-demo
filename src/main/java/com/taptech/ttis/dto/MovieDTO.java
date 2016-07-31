package com.taptech.ttis.dto;

/**
 * Created by tap on 7/30/16.
 */
public class MovieDTO {

    private String movieId;
    private String movieName;
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieDTO movieDTO = (MovieDTO) o;

        return movieId != null ? movieId.equals(movieDTO.movieId) : movieDTO.movieId == null;

    }

    @Override
    public int hashCode() {
        return movieId != null ? movieId.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MovieDTO{");
        sb.append("genre='").append(genre).append('\'');
        sb.append(", movieId='").append(movieId).append('\'');
        sb.append(", movieName='").append(movieName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
