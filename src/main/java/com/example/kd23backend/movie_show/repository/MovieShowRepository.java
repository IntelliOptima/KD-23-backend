package com.example.kd23backend.movie_show.repository;

import com.example.kd23backend.movie_show.model.MovieShow;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieShowRepository extends JpaRepository<MovieShow, Integer> {

    List<MovieShow> findAllByStartDateTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<MovieShow> findAllByTheaterIdAndTheater_Cinema_IdAndStartDateTimeIsGreaterThanEqualAndStartDateTimeIsLessThanEqual(
            Integer theaterId, Integer cinemaId, LocalDateTime startTime, LocalDateTime endTime );

    Optional<MovieShow> findById(int id);
}
