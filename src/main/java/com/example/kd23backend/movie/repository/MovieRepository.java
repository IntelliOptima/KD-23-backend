package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT m.id FROM Movie m")
    Set<Integer> findAllIds();

    List<Movie> findAllByTitleContainsIgnoreCase(String title);

    Optional<Movie> findByTitle(String title);

    List<Movie> findByReleaseDate(LocalDate releaseDate);

    List<Movie> findAllByActorsContaining(Actor actor);

    List<Movie> findAllByGenresContaining(Genre genre);

    List<Movie> findAllByIsAdultTrue();

    List<Movie> findAllByIsAdultFalse();

    List<Movie> findAllByPosterIsNot(String poster);
    List<Movie> findAllByTrailerIsNot(String poster);

    List<Movie> findAllByRuntimeLessThan(Integer runtime);

    List<Movie> findAllByVoteRatingIsGreaterThan(Double voteRating);
}
