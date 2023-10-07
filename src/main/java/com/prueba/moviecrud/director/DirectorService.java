package com.prueba.moviecrud.director;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    
    private DirectorRepository directorRepository;
    private ModelMapper modelMapper;


    public DirectorService(DirectorRepository directorRepository, ModelMapper modelMapper) {
        this.directorRepository = directorRepository;
        this.modelMapper = modelMapper;
    }

    public List<DirectorDTO> getAllDirectors(){
        return directorRepository.findAll().stream()
            .map(director -> modelMapper.map(director, DirectorDTO.class))
            .collect(Collectors.toList());
    }
    
    public DirectorDTO getDirectorById(Long id){
        return modelMapper.map(directorRepository.getReferenceById(id), DirectorDTO.class);
    }

    public DirectorDTO createDirector(DirectorDTO directorDTO){
        return modelMapper.map(directorRepository.save(modelMapper.map(directorDTO, Director.class)), DirectorDTO.class);
    }


    public DirectorDTO updateDirector(Long id, DirectorDTO directorDTO){
        DirectorDTO directorDTOToUpdate = getDirectorById(id);
        directorDTOToUpdate.setFirstName(directorDTO.getFirstName());
        directorDTOToUpdate.setLastName(directorDTO.getLastName());
        Director director = modelMapper.map(directorDTOToUpdate, Director.class);

        return modelMapper.map(directorRepository.save(director), DirectorDTO.class);
    }

    public void deleteDirector(Long id){
        directorRepository.deleteById(id);
    }
    

}
