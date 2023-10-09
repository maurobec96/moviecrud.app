package com.prueba.moviecrud.genre;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prueba.moviecrud.movie.Movie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_GENRE")
    private Long id;

    @Column(name = "GENRE_NAME", length = 25, nullable = false, unique = true)
    private String genreName; 

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    Set<Movie> movies;


    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreName() {
        return this.genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Set<Movie> getMovies() {
        return this.movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    
}
