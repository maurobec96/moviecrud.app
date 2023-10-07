package com.prueba.moviecrud.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.prueba.moviecrud.director.DirectorRepository;
import com.prueba.moviecrud.genre.GenreRepository;

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

    public MovieDTO getMovieById(Long id){
        return modelMapper.map(movieRepository.getReferenceById(id), MovieDTO.class);
    }

    public MovieDTO createMovie(MovieDTO movieDTO){
        Movie movie = getMovieFromDTO(movieDTO);
        return mapToDTO(movie);
    }

    public MovieDTO updateMovie(Long id, MovieDTO movieDTO){
        Movie movie = movieRepository.getReferenceById(id);
        setMovieValues(movieDTO, movie);
        return mapToDTO(movie);
    }
    
    public void deleteMovie(Long id){
        Movie movie = movieRepository.getReferenceById(id);
        movie.getGenres().stream().forEach(genre -> genre.getMovies().remove(movie));
        movieRepository.delete(movie);
    }
    
    private Movie getMovieFromDTO(MovieDTO movieDTO) {
        Movie movie = new Movie();
        setMovieValues(movieDTO, movie);
        return movie;
    }
    
    private MovieDTO mapToDTO(Movie movie) {
        return modelMapper.map(movieRepository.save(movie), MovieDTO.class);
    }

    private void setMovieValues(MovieDTO movieDTO, Movie movie) {
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setReleaseYear(movieDTO.getReleaseYear());
        movie.setDirector(directorRepository.getReferenceById(movieDTO.getDirector().getId()));
        movie.setGenres(movieDTO.getGenres().stream().map(GenreDTO -> genreRepository.getReferenceById(GenreDTO.getId())).collect(Collectors.toSet()));
    }



    
    
}
