package com.example.kd23backend.movie.service;


import com.example.kd23backend.movie.model.Movie;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public interface IMovieAPIService {
    void fetchAllMovies() throws IOException;

    Movie fetchOneMovie(Integer id);

    void saveAllMovies();

}
