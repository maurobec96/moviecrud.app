package com.prueba.moviecrud.movie;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prueba.moviecrud.director.DirectorDTO;
import com.prueba.moviecrud.genre.GenreDTO;

public class MovieDTO {
    
    @JsonFormat(shape =  JsonFormat.Shape.STRING)
    private Long id;

    private String title;

    @JsonFormat(shape =  JsonFormat.Shape.STRING)
    private Integer releaseYear;
    
    private String description;
    private DirectorDTO director;
    private List<GenreDTO> genres;

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
    public DirectorDTO getDirector() {
        return director;
    }
    public void setDirector(DirectorDTO director) {
        this.director = director;
    }
    public List<GenreDTO> getGenres() {
        return genres;
    }
    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    

}
