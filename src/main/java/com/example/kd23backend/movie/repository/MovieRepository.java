package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @EntityGraph(attributePaths = {"actors", "genres"})
    @Query("SELECT m FROM Movie m WHERE m.id = :id")
    Optional<Movie> findByIdWithActorsAndGenres(@Param("id") Integer id);


}
