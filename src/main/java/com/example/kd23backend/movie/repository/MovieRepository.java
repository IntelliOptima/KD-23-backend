package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    @Query("SELECT m.id FROM Movie m")
    Set<Integer> findAllIds();

    List<Movie> findAllByTitleContainsIgnoreCaseOrderByTitle(String title, Pageable pageable);

    List<Movie> findByReleaseDateOrderByReleaseDate(LocalDate releaseDate, Pageable pageable);

    List<Movie> findAllByActorsContainingIgnoreCaseOrderByTitle(Actor actor, Pageable pageable);

    List<Movie> findAllByGenresContainingIgnoreCaseOrderByTitle(Genre genre, Pageable pageable);

    List<Movie> findAllByIsAdultTrue(Pageable pageable);

    List<Movie> findAllByIsAdultFalseOrderByTitle(Pageable pageable);

    List<Movie> findAllByPosterIsNotOrderByTitle(String poster, Pageable pageable);
    List<Movie> findAllByTrailerIsNotOrderByTitle(String poster, Pageable pageable);

    List<Movie> findAllByRuntimeLessThanOrderByRuntimeDesc(Integer runtime, Pageable pageable);

    List<Movie> findAllByVoteRatingIsGreaterThanOrderByVoteRating(Double voteRating, Pageable pageable);
}
