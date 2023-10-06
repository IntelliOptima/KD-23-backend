package com.example.kd23backend.movie.service.interfaces;


import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMovieService extends IGenreService, IActorService {
    List<Movie> getAllMovies(Pageable pageable);
    Optional<Movie> getSpecificMovie(Integer id);

    List<Movie> findAllByTitleContains(String title, Pageable pageable);

    List<Movie> findByReleaseDate(LocalDate releaseDate, Pageable pageable);

    List<Movie> findAllByIsAdultTrue(Pageable pageable);
    List<Movie> findAllByIsAdultFalse(Pageable pageable);


    List<Movie> findAllByActorsContaining(Actor actor, Pageable pageable);

    List<Movie> findAllByGenresContaining(Genre genre, Pageable pageable);

    List<Movie> findAllByPosterIsNot(String poster, Pageable pageable);
    List<Movie> findAllByTrailerIsNot(String poster,Pageable pageable);

    List<Movie> findAllByRuntimeLessThan(Integer runtime, Pageable pageable);

    List<Movie> findAllByVoteRatingIsGreaterThan(Double voteRating, Pageable pageable);

}
