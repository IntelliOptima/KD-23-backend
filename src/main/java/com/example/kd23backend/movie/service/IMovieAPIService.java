package com.example.kd23backend.movie.service;


import com.example.kd23backend.movie.model.Movie;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public interface IMovieAPIService {
    void fetchMovies() throws IOException;
    void fetchActors_And_AddToMovies(Set<Movie> movies);

    void fetchTrailers_And_AddToMovies(Set<Movie> movies);

    void saveAllMovies(Set<Movie> movies);

}
