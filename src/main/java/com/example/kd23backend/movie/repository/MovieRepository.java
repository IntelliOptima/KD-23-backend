package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT m.id FROM Movie m")
    Set<Integer> findAllIds();


}
