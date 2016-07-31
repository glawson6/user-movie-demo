/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taptech.ttis.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tap
 */
@Entity
@Table(name = "movie_rating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieRating.findAll", query = "SELECT m FROM MovieRating m"),
    @NamedQuery(name = "MovieRating.findById", query = "SELECT m FROM MovieRating m WHERE m.id = :id"),
    @NamedQuery(name = "MovieRating.findByRating", query = "SELECT m FROM MovieRating m WHERE m.rating = :rating")})
public class MovieRating implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating")
    private int rating;
    @JoinColumn(name = "movieId", referencedColumnName = "movieId")
    @ManyToOne(optional = false)
    private Movie movieId;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private User userId;

    public MovieRating() {
    }

    public MovieRating(String id) {
        this.id = id;
    }

    public MovieRating(String id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieRating)) {
            return false;
        }
        MovieRating other = (MovieRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.taptech.ttis.entity.MovieRating[ id=" + id + " ]";
    }
    
}
