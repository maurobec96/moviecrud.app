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
        return mapToDTO(genreRepository.getReferenceById(id));
    }

    public GenreDTO createGenre(GenreDTO genreDTO){
        return mapToDTO(genreRepository.save(modelMapper.map(genreDTO, Genre.class)));
    }


    public GenreDTO updateGenre(Long id, GenreDTO genreDTO){
        GenreDTO genreToUpdate = getGenreById(id);
        genreToUpdate.setGenreName(genreDTO.getGenreName());
        Genre genre = modelMapper.map(genreToUpdate, Genre.class);
        return mapToDTO(genreRepository.save(genre));
    }

    public void deleteGenre(Long id){
        genreRepository.deleteById(id);
    }

    private GenreDTO mapToDTO(Genre genre){
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setGenreName(genre.getGenreName());
        return genreDTO;
    }
    

}
