package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT m.id FROM Movie m")
    Set<Integer> findAllIds();

    List<Movie> findAllByTitleContains(String title);

    Optional<Movie> findByTitle(String title);
    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g WHERE LOWER(g.name) LIKE ?1")
    List<Movie> findAllByGenresContainingKeywordIgnoreCase(String keyword);
    List<Movie> findAllByActorsContaining(String actor);

    List<Movie> findByReleaseDate(LocalDate releaseDate);

    List<Movie> findAllByIsAdultTrue();

    List<Movie> findAllByIsAdultFalse();

    List<Movie> findAllByPosterIsNot(String poster);
    List<Movie> findAllByTrailerIsNot(String poster);

    List<Movie> findAllByRuntimeLessThan(Integer runtime);

    List<Movie> findAllByVoteRatingIsGreaterThan(Double voteRating);
}
