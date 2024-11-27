package com.trabalhodeback.locadoradigital.service;

import com.trabalhodeback.locadoradigital.exception.MovieNotFoundException;
import com.trabalhodeback.locadoradigital.model.Movie;
import com.trabalhodeback.locadoradigital.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetMovieByIdUseCase {

   @Autowired
    private MovieRepository movieRepository;

    public Movie execute(UUID id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if(optionalMovie.isEmpty()){
            throw new MovieNotFoundException();
        }

        return optionalMovie.get();
    }


}
