package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findById(int genreId);

    List<Genre> findByNameContainingIgnoreCase(String genreName);

    @Query(value = "SELECT g.* FROM Genre g " +
            "INNER JOIN movie_genre mg ON g.id = mg.genre_id " +
            "WHERE mg.movie_id = :movieId",
            nativeQuery = true)
    List<Genre> findAllByMoviesContaining(@Param("movieId") Integer movieId);
}

