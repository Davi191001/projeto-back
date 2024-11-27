package com.trabalhodeback.locadoradigital.usecase;

import com.trabalhodeback.locadoradigital.exception.MovieNotFoundException;
import com.trabalhodeback.locadoradigital.model.Movie;
import com.trabalhodeback.locadoradigital.repository.MovieRepository;
import com.trabalhodeback.locadoradigital.service.GetMovieByIdUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class GetMovieByIdUseCaseTest {
    @Mock
    private MovieRepository MovieRepository;

    @InjectMocks
    private GetMovieByIdUseCase getMovieByIdUseCase;

    @Test
    void execute() {
        UUID uuid = UUID.randomUUID();
        Movie movie = new Movie();
        movie.setId(uuid);
        movie.setTitulo("O Senhor dos Anéis");
        movie.setProdutora("J.R.R. Tolkien");
        movie.setGenero("Fantasia");
        movie.setAnoLançamento(1954);
        movie.setNumeroEstoque(5);

        when(movieRepository.findById(any())).thenReturn(Optional.of(movie));

        Movie movieReturned = getMovieByIdUseCase.execute(uuid);

        assertThat(movieReturned.getId()).isEqualTo(uuid);
    }

    @Test
    void execute2() {
        UUID uuid = UUID.randomUUID();
        Movie movie = new Movie();
        movie.setId(uuid);
        movie.setTitulo("O Senhor dos Anéis");
        movie.setProdutora("J.R.R. Tolkien");
        movie.setGenero("Fantasia");
        movie.setAnoLançamento(1954);
        movie.setNumeroEstoque(5);

        when(moveRepository.findById(any())).thenReturn(Optional.of(movie));

        getMovieByIdUseCase.execute(uuid);

        verify(movieRepository, times(1)).findById(any());
    }

    @Test
    void execute3() {
        UUID uuid = UUID.randomUUID();
        Movie movie = new Movie();
        movie.setId(uuid);
        movie.setTitulo("O Senhor dos Anéis");
        movie.setProdutora("J.R.R. Tolkien");
        movie.setGenero("Fantasia");
        movie.setAnoLançamento(1954);
        movie.setNumeroEstoque(5);

        when(movieRepository.findById(any())).thenReturn(Optional.of(movie));

        assertDoesNotThrow(() -> getMovieByIdUseCase.execute(uuid));
    }

    @Test
    void executeWithException() {
        when(movieRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> getMovieByIdUseCase.execute(UUID.randomUUID()));
    }
}

