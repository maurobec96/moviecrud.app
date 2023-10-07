package com.prueba.moviecrud.director;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/director")
public class DirectorController {
    
    private DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public ResponseEntity<List<DirectorDTO>> getAllDirectors() {
        return new ResponseEntity<>(directorService.getAllDirectors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(directorService.getDirectorById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found");
        } catch (Exception e2){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorDTO directorDTO) {
        try{
        return new ResponseEntity<>(directorService.createDirector(directorDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException  e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Property not Valid");
        } catch (Exception e2){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable Long id, @RequestBody DirectorDTO directorDTO) {
        try {
            return new ResponseEntity<>(directorService.updateDirector(id, directorDTO), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found");
        } catch (Exception e2){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        try {
            directorService.deleteDirector(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    



}
