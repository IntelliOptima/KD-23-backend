package com.example.kd23backend.movie.service;


import com.example.kd23backend.movie.model.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<Movie> getAllMovies();
    Optional<Movie> getSpecificMovie(Integer id);

    List<Movie> findAllByTitleContains(String title);
    List<Movie> findAllByGenresContainingKeywordIgnoreCase(String genre);
    List<Movie> findAllByActorsContains(String actor);

    List<Movie> findByReleaseDate(LocalDate releaseDate);

    List<Movie> findAllByIsAdultTrue();
    List<Movie> findAllByIsAdultFalse();

    Optional<Movie> findByTitle(String string);

    List<Movie> findAllByPosterIsNot(String poster);
    List<Movie> findAllByTrailerIsNot(String poster);

    List<Movie> findAllByRuntimeLessThan(Integer runtime);

    List<Movie> findAllByVoteRatingIsGreaterThan(Double voteRating);
}
