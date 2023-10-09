package com.example.kd23backend.movie_show.repository;

import com.example.kd23backend.movie_show.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieShowRepository extends JpaRepository<MovieShow, Integer> {

    List<MovieShow> findAllByStartDateTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    Optional<MovieShow> findById(int id);
}
