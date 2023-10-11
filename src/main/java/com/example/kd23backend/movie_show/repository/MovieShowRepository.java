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
    @Query(value = "SELECT m.* FROM MovieShow m " +
            "INNER JOIN program po ON m.id = po.program_id " +
            "WHERE po.program_id = :programId",
            nativeQuery = true)
    List<MovieShow> findAllByProgramContaining(@Param("movieId") Integer programId);
    List<MovieShow> findAllByStartDateTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<MovieShow> findAllByTheaterIdAndTheater_Cinema_IdAndStartDateTimeIsGreaterThanEqualAndStartDateTimeIsLessThanEqual(
            Integer theaterId, Integer cinemaId, LocalDateTime startTime, LocalDateTime endTime );

    Optional<MovieShow> findById(int id);
}
