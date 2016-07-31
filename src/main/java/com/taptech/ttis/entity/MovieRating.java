/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taptech.ttis.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author tap
 */
@Entity
@Table(name = "movie_rating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieRating.findAll", query = "SELECT m FROM MovieRating m"),
    @NamedQuery(name = "MovieRating.findByid", query = "SELECT m FROM MovieRating m WHERE m.id = :id"),
        @NamedQuery(name = "MovieRating.findAverageRating", query = "SELECT AVG(m.rating) FROM MovieRating m WHERE m.movieid = :movieid"),
    @NamedQuery(name = "MovieRating.findByRating", query = "SELECT m FROM MovieRating m WHERE m.rating = :rating")})
public class MovieRating implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating")
    private double rating;
    @JoinColumn(name = "movieid", referencedColumnName = "movieid")
    @ManyToOne(optional = false)
    private Movie movieid;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private User userid;

    public MovieRating() {
    }

    public MovieRating(String id) {
        this.id = id;
    }

    public MovieRating(String id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Movie getMovieid() {
        return movieid;
    }

    public void setMovieid(Movie movieid) {
        this.movieid = movieid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
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
