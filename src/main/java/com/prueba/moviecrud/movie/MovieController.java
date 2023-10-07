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

import com.prueba.moviecrud.director.DirectorDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@Tag(name = "Movie", description = "Movie management APIs")
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

    @Operation(
      summary = "Retrieve a Movie by Id",
      description = """
        Get a Movie object by specifying its id. 
        The response is Movie object with id, title, description, release year, director object and a list of genre objects.
              """)
    @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MovieDTO.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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


    @Operation(
      summary = "Create a Movie",
      description = """
        Create a Movie object. 
        The response is Movie object with id, title, description, release year, director object and a list of genre objects.
        """)
    @ApiResponses({
      @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = DirectorDTO.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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

    @Operation(
      summary = "Update a Movie by Id",
      description = """
        Update a Movie object by specifying its id and the fields to modify. 
        The response is Movie object with id, title, description, release year, director object and a list of genre objects.
                """)
    @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = DirectorDTO.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> createMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        try{
        return new ResponseEntity<>(movieService.updateMovie(id, movieDTO), HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException  e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        } catch (Exception e2){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
        }
    }

    @Operation(
      summary = "Delete a Movie by Id",
      description = "Delete a Movie object by specifying its id.")
    @ApiResponses({
      @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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
