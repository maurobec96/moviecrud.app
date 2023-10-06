package com.prueba.moviecrud.Genre;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
@RequestMapping("api/Genre")
public class GenreController {
    
    private GenreService GenreService;
    private ModelMapper modelMapper;


    public GenreController(GenreService GenreService, ModelMapper modelMapper) {
        this.GenreService = GenreService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<Genre> Genres = GenreService.getAllGenres();
        List<GenreDTO> GenreDTOs = Genres.stream()
                .map(Genre -> modelMapper.map(Genre, GenreDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(GenreDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        try {
            Genre Genre = GenreService.getGenreById(id);
            GenreDTO GenreDTO = modelMapper.map(Genre, GenreDTO.class);
            return new ResponseEntity<>(GenreDTO, HttpStatus.OK);
            
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found");
        } catch (Exception e2){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO GenreDTO) {
        try{
        Genre Genre = modelMapper.map(GenreDTO, Genre.class);
        Genre createdGenre = GenreService.createGenre(Genre);
        GenreDTO createdGenreDTO = modelMapper.map(createdGenre, GenreDTO.class);
        return new ResponseEntity<>(createdGenreDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException  e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Property not Valid");
        } catch (Exception e2){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, @RequestBody GenreDTO GenreDTO) {
        try {
        Genre Genre = modelMapper.map(GenreDTO, Genre.class);
        Genre updatedGenre = GenreService.updateGenre(id, Genre);
            GenreDTO updatedGenreDTO = modelMapper.map(updatedGenre, GenreDTO.class);
            return new ResponseEntity<>(updatedGenreDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found");
        } catch (Exception e2){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e2.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        try {
            GenreService.deleteGenre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    



}
