package com.prueba.moviecrud.director;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    
    private DirectorRepository directorRepository;


    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<Director> getAllDirectors(){
        return directorRepository.findAll();
    }

    public Director createDirector(Director director){
        return directorRepository.save(director);
    }

    public Director getDirectorById(Long id){
        return directorRepository.getReferenceById(id);
    }

    public Director updateDirector(Long id, Director director){
        Director directorToUpdate = getDirectorById(id);
        directorToUpdate.setFirstName(director.getFirstName());
        directorToUpdate.setLastName(director.getLastName());
        return directorRepository.save(directorToUpdate);
    }

    public void deleteDirector(Long id){
        directorRepository.deleteById(id);
    }
    

}
