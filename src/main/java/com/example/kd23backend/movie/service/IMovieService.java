package com.example.kd23backend.movie.service;


import com.example.kd23backend.movie.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface IMovieService {
    List<Movie> getAllMovies();
    Movie getSpecificMovie(Integer id);

    List<Movie> findAllByTitle(String title);

    List<Movie> findByReleaseDate(LocalDate releaseDate);

    List<Movie> findAllByIsAdultTrue();
    List<Movie> findAllByIsAdultFalse();

    Movie findByTitle(String string);

    List<Movie> findAllByPosterIsNot(String poster);

    List<Movie> findAllByRuntimeLessThan(Integer runtime);

    List<Movie> findAllByVoteRatingIsGreaterThan(Double voteRating);
}
