package com.example.kd23backend.movie.service;

import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getSpecificMovie(Integer id) {
        return movieRepository.findById(id).orElseThrow();
    }

    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void editMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }
}
