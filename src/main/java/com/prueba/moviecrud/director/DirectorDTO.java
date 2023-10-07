package com.prueba.moviecrud.director;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DirectorDTO{

    @JsonFormat(shape =  JsonFormat.Shape.STRING)
    private Long id;

    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
