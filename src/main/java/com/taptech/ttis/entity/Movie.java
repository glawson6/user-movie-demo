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
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author tap
 */
@Entity
@Table(name = "movie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
    @NamedQuery(name = "Movie.findByMovieid", query = "SELECT m FROM Movie m WHERE m.movieid = :movieid"),
    @NamedQuery(name = "Movie.findByName", query = "SELECT m FROM Movie m WHERE m.name = :name")})
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "movieid")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String movieid;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    @JoinColumn(name = "genreid", referencedColumnName = "genreid")
    @ManyToOne(optional = false)
    private Genre genreid;

    @Basic(optional = false)
    @NotNull
    @Column(name = "externalid")
    private int externalid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieid")
    private Collection<MovieRating> movieRatingCollection;

    public Movie() {
    }

    public Movie(String movieid) {
        this.movieid = movieid;
    }

    public Movie(String movieid, String name) {
        this.movieid = movieid;
        this.name = name;
    }

    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenreid() {
        return genreid;
    }

    public void setGenreid(Genre genreid) {
        this.genreid = genreid;
    }

    @XmlTransient
    public Collection<MovieRating> getMovieRatingCollection() {
        return movieRatingCollection;
    }

    public void setMovieRatingCollection(Collection<MovieRating> movieRatingCollection) {
        this.movieRatingCollection = movieRatingCollection;
    }

    public int getExternalid() {
        return externalid;
    }

    public void setExternalid(int externalid) {
        this.externalid = externalid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieid != null ? movieid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.movieid == null && other.movieid != null) || (this.movieid != null && !this.movieid.equals(other.movieid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.taptech.ttis.entity.Movie[ movieid=" + movieid + " ]";
    }

}
