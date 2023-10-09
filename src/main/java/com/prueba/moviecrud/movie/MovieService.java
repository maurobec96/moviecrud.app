package com.prueba.moviecrud.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.prueba.moviecrud.director.DirectorDTO;
import com.prueba.moviecrud.director.DirectorRepository;
import com.prueba.moviecrud.genre.GenreDTO;
import com.prueba.moviecrud.genre.GenreRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private ModelMapper modelMapper;
    private GenreRepository genreRepository;
    private DirectorRepository directorRepository;

    

    public MovieService(MovieRepository movieRepository, ModelMapper modelMapper, GenreRepository genreRepository,
        DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.genreRepository = genreRepository;
        this.directorRepository = directorRepository;
    }



    public List<MovieDTO> getAllMovies(){
        return movieRepository.findAll().stream()
            .map(movie -> modelMapper.map(movie, MovieDTO.class))
            .collect(Collectors.toList());
    }

    public MovieDTO getMovieById(Long id) throws EntityNotFoundException{
        Movie movie = movieRepository.getReferenceById(id);
        return mapToDTO(movie);
    }

    public MovieDTO createMovie(MovieDTO movieDTO){
        // Movie movie = getMovieFromDTO(movieDTO);
        Movie movie = new Movie();
        mapFromDTO(movieDTO, movie);
        return mapToDTO(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(Long id, MovieDTO movieDTO){
        Movie movie = movieRepository.getReferenceById(id);
        mapFromDTO(movieDTO, movie);
        return mapToDTO(movieRepository.save(movie));
    }
    
    public void deleteMovie(Long id){
        Movie movie = movieRepository.getReferenceById(id);
        movie.getGenres().stream().forEach(genre -> genre.getMovies().remove(movie));
        movieRepository.delete(movie);
    }
    
    private MovieDTO mapToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setReleaseYear(movie.getReleaseYear());
        movieDTO.setDirector(modelMapper.map(movie.getDirector(), DirectorDTO.class));
        movieDTO.setGenres(movie.getGenres().stream().map(genre -> modelMapper.map(genre, GenreDTO.class)).collect(Collectors.toList()));
        return movieDTO;
    }

    private void mapFromDTO(MovieDTO movieDTO, Movie movie) {
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setReleaseYear(movieDTO.getReleaseYear());
        movie.setDirector(directorRepository.getReferenceById(movieDTO.getDirector().getId()));
        movie.setGenres(movieDTO.getGenres().stream().map(GenreDTO -> genreRepository.getReferenceById(GenreDTO.getId())).collect(Collectors.toSet()));
    }




    
    
}
