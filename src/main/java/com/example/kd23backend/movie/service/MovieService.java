package com.example.kd23backend.movie.service;

import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public List<Movie> findAllByTitle(String title) {
        return movieRepository.findAllByTitle(title);
    }

    @Override
    public Movie findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findByReleaseDate(LocalDate releaseDate) {
        return movieRepository.findByReleaseDate(releaseDate);
    }

    @Override
    public List<Movie> findAllByIsAdultTrue() {
        return movieRepository.findAllByIsAdultTrue();
    }

    @Override
    public List<Movie> findAllByIsAdultFalse() {
        return movieRepository.findAllByIsAdultFalse();
    }

    @Override
    public List<Movie> findAllByPosterIsNot(String poster) {
        return movieRepository.findAllByPosterIsNot(poster);
    }

    @Override
    public List<Movie> findAllByRuntimeLessThan(Integer runtime) {
        return movieRepository.findAllByRuntimeLessThan(runtime);
    }

    @Override
    public List<Movie> findAllByVoteRatingIsGreaterThan(Double voteRating) {
        return movieRepository.findAllByVoteRatingIsGreaterThan(voteRating);
    }
}
