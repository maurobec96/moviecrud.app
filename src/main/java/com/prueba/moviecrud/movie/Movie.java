package com.prueba.moviecrud.movie;

import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.prueba.moviecrud.director.Director;
import com.prueba.moviecrud.genre.Genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_MOVIE")
    private Long id;

    @Column(name="TITLE", length = 100, nullable = false, unique = true)
    private String title;
    
    @Column(name="RELEASE_YEAR", length=4, nullable=false)
    private Integer releaseYear;

    @Column(name="DESCRIPTION", length=1000, nullable=false)
    private String description;

    @ManyToOne
    @JoinColumn(name="ID_DIRECTOR")
    private Director director;

    @ManyToAny
    @JoinTable(
        name="MOVIE_GENRES",
        joinColumns = @JoinColumn(name="ID_MOVIE"),
        inverseJoinColumns = @JoinColumn(name="ID_GENRE")
    )
    private Set<Genre> genres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }


    


    
}