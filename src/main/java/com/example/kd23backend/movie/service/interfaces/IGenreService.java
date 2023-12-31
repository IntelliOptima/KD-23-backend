package com.example.kd23backend.movie.service.interfaces;

import com.example.kd23backend.movie.model.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGenreService {

    Genre findGenreById(int genreId);

    List<Genre> findGenreByNameContainingIgnoreCase(String genreName);

    List<Genre> findAllGenresByMoviesContaining(@Param("movieId") Integer movieId, Pageable pageable);
}
