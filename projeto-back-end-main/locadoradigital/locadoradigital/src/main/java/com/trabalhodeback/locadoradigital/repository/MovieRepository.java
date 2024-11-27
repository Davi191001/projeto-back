package com.trabalhodeback.locadoradigital.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalhodeback.locadoradigital.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    

    Optional<Movie> findByTitulo(String titulo);

   
    boolean existsByTitulo(String titulo);


}
