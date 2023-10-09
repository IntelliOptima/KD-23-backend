package com.example.kd23backend.movie_show.repository;

import com.example.kd23backend.movie_show.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovieShowRepository extends JpaRepository<MovieShow, Integer> {
    List<MovieShow> getMovieShowByDate(LocalDate date);
}
