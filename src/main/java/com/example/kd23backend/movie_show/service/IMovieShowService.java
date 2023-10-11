package com.example.kd23backend.movie_show.service;

import com.example.kd23backend.movie_show.model.MovieShow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IMovieShowService {

    List<MovieShow> findAllByStartDateTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    Optional<MovieShow> findMovieShowById(Integer id);

    List<MovieShow> findAllByTheaterForTimePeriod(Integer theaterId, Integer cinemaId, LocalDateTime startTime, LocalDateTime endTime );
}
