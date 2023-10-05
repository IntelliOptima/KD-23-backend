package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query("SELECT g.id FROM Genre g")
    Set<Integer> findAllIds();
}
