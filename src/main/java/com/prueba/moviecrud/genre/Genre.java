package com.prueba.moviecrud.genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_GENRE")
    private Long id;

    @Column(name = "GENRE_NAME", length = 25, nullable = false)
    private String genreName; 


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
    
}
