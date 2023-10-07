package com.prueba.moviecrud.genre;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    
    private GenreRepository genreRepository;
    private ModelMapper modelMapper;

    public GenreService(GenreRepository genreRepository, ModelMapper modelMapper) {
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    public List<GenreDTO> getAllGenres(){
        return genreRepository.findAll().stream()
                .map(genre -> modelMapper.map(genre, GenreDTO.class))
                .collect(Collectors.toList());
    }

    public GenreDTO getGenreById(Long id){
        return modelMapper.map(genreRepository.getReferenceById(id), GenreDTO.class);
    }

    public GenreDTO createGenre(GenreDTO genreDTO){
        return modelMapper.map(genreRepository.save(modelMapper.map(genreDTO, Genre.class)),GenreDTO.class);
    }


    public GenreDTO updateGenre(Long id, GenreDTO genreDTO){
        GenreDTO genreToUpdate = getGenreById(id);
        genreToUpdate.setGenreName(genreDTO.getGenreName());
        Genre genre = modelMapper.map(genreToUpdate, Genre.class);

        return modelMapper.map(genreRepository.save(genre), GenreDTO.class);
    }

    public void deleteGenre(Long id){
        genreRepository.deleteById(id);
    }
    

}
