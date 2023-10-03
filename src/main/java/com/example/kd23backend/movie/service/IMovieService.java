package com.example.kd23backend.movie.service;


import com.example.kd23backend.movie.model.Movie;

import java.util.List;

public interface IMovieService {
    List<Movie> getAllMovies();
    Movie getSpecificMovie(Integer id);
    void addMovie(Movie movie);
    void editMovie(Movie movie);
    void deleteMovie(Integer id);
}
