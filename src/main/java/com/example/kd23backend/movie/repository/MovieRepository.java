package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Integer, Movie> {


}
