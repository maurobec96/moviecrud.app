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
        return mapToDTO(directorRepository.getReferenceById(id));
    }

    public DirectorDTO createDirector(DirectorDTO directorDTO){
        return mapToDTO(directorRepository.save(modelMapper.map(directorDTO, Director.class)));
    }


    public DirectorDTO updateDirector(Long id, DirectorDTO directorDTO){
        DirectorDTO directorDTOToUpdate = getDirectorById(id);
        directorDTOToUpdate.setFirstName(directorDTO.getFirstName());
        directorDTOToUpdate.setLastName(directorDTO.getLastName());
        Director director = modelMapper.map(directorDTOToUpdate, Director.class);

        return mapToDTO(directorRepository.save(director));
    }

    public void deleteDirector(Long id){
        directorRepository.deleteById(id);
    }

    private DirectorDTO mapToDTO(Director director){
        DirectorDTO directorDTO = new DirectorDTO();
        directorDTO.setId(director.getId());
        directorDTO.setFirstName(director.getFirstName());
        directorDTO.setLastName(director.getLastName());
        return directorDTO;
    }
    

}
