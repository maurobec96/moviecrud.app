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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@Tag(name = "Director", description = "Director management APIs")
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

    @Operation(
      summary = "Retrieve a Director by Id",
      description = """
          Get a Director object by specifying its id. 
          The response is Director object with id, first name, and last name.
              """)
    @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = DirectorDTO.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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

    @Operation(
      summary = "Create a Director",
      description = """
          Create a Director object. 
          The response is Director object with id, first name, and last name.
              """)
    @ApiResponses({
      @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = DirectorDTO.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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

    @Operation(
      summary = "Update a Director by Id",
      description = """
            Update a Director object by specifying its id and the fields to modify. 
            The response is Director object with id, first name, and last name.
              """)
    @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = DirectorDTO.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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

    @Operation(
      summary = "Delete a Director by Id",
      description = "Delete a Director object by specifying its id.")
    @ApiResponses({
      @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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
