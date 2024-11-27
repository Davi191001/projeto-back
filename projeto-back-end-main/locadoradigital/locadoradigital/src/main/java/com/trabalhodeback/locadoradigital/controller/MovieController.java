package com.trabalhodeback.locadoradigital.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhodeback.locadoradigital.dto.MovieDto;
import com.trabalhodeback.locadoradigital.model.Movie;
import com.trabalhodeback.locadoradigital.repository.MovieRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/movie")
@Tag(name = "movie", description = "Endpoints for managing movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;


    @Secured("ROLE_ADMIN")
    @Operation(
            summary = "Create a new movie",
            description = "Creates a new movie entry in the database.",
            method = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/movie")
    public ResponseEntity<Movie> createBook(@RequestBody MovieDto movieDto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDto, movie);

        Movie savedMovie = movieRepository.save(movie);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);

    }


    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable UUID id) {
        return movieRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Secured("ROLE_ADMIN")
    @Operation(
            summary = "Update a movie by ID",
            description = "Updates an existing movie entry by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable UUID id, @RequestBody @Valid MovieDto movieDto) {
        return movieRepository.findById(id)
                .map(existingMovie -> {
                    BeanUtils.copyProperties(movieDto, existingMovie, "id");
                    Movie updatedMovie = movieRepository.save(existingMovie);
                    return ResponseEntity.ok(updatedMovie);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @Secured("ROLE_ADMIN")
    @Operation(
            summary = "Delete a movie by ID",
            description = "Deletes a movie entry from the database by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "List all movies with pagination")
    @GetMapping
    public ResponseEntity<Page<Mvie>> listMovie(@PageableDefault(size = 10) Pageable pageable) {
        Page<Movie> movie = bookRepository.findAll(pageable);
        return ResponseEntity.ok(movie);
    }
}
