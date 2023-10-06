package com.prueba.moviecrud.genre;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GenreService {
    
    private GenreRepository GenreRepository;


    public GenreService(GenreRepository GenreRepository) {
        this.GenreRepository = GenreRepository;
    }

    public List<Genre> getAllGenres(){
        return GenreRepository.findAll();
    }

    public Genre createGenre(Genre Genre){
        return GenreRepository.save(Genre);
    }

    public Genre getGenreById(Long id){
        return GenreRepository.getReferenceById(id);
    }

    public Genre updateGenre(Long id, Genre Genre){
        Genre GenreToUpdate = getGenreById(id);
        GenreToUpdate.setGenreName(Genre.getGenreName());
        return GenreRepository.save(GenreToUpdate);
    }

    public void deleteGenre(Long id){
        GenreRepository.deleteById(id);
    }
    

}
