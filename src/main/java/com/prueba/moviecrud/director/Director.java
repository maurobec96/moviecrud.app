package com.prueba.moviecrud.director;



import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prueba.moviecrud.movie.Movie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_DIRECTOR")
    private Long id;

    @Column(name = "FIRST_NAME", length = 255, nullable = false, unique = true)
    private String firstName;

    @Column(name = "LAST_NAME", length = 255, nullable = false, unique = true)
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "director")
    @JsonIgnore
    private Set<Movie> movies;



    public Director() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Set<Movie> getMovies() {
        return this.movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    
}