package com.prueba.moviecrud.movie;

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
@RequestMapping("api/movie")
public class MovieController {

    private MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        } catch (Exception e2){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO) {
        try{
        return new ResponseEntity<>(movieService.createMovie(movieDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException  e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Property not Valid");
        } catch (Exception e2){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<MovieDTO> createMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        try{
        return new ResponseEntity<>(movieService.updateMovie(id, movieDTO), HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException  e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        } catch (Exception e2){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        try {
            movieService.deleteMovie(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
}
