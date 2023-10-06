package com.prueba.moviecrud.director;

import org.springframework.data.jpa.repository.JpaRepository;

// TODO https://www.javaguides.net/2021/02/spring-boot-dto-example-entity-to-dto.html
public interface DirectorRepository extends JpaRepository<Director, Long> {

}
