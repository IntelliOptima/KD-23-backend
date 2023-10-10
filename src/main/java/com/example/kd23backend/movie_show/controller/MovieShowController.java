package com.example.kd23backend.movie_show.controller;

import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.movie_show.service.MovieShowService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/movie-show")
public class MovieShowController {

    private final MovieShowService movieShowService;

    public MovieShowController(MovieShowService movieShowService){
        this.movieShowService = movieShowService;
    }



    @GetMapping("/find-all-by-date/{date}")
    public ResponseEntity<List<MovieShow>> findAllMovieShowsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<MovieShow> movieShows = movieShowService.findAllByStartDateTimeBetween(startOfDay, endOfDay);
        return movieShows.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movieShows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieShow> findMovieShowById(@PathVariable Integer id){
        Optional<MovieShow> movieShow = movieShowService.findMovieShowById(id);
        return movieShow.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("theater=/{theaterId}/startDate=/{startDate}/endDate=/{endDate}/cinema=/{cinemaId}")
    public ResponseEntity<List<MovieShow>> getMovieShowsForTheaterByTimePeriod(@PathVariable Integer theaterId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @PathVariable @DateTimeFormat(pattern = "yyy-MM-dd") LocalDate endDate,
            @PathVariable Integer cinemaId) {
        System.out.println("theaterid = " +  theaterId + " start = " + startDate + " enddate =" + endDate + cinemaId);
        List<MovieShow> movieShows = movieShowService.findAllByTheaterForTimePeriod(theaterId, cinemaId, startDate, endDate);
        return movieShows.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movieShows);
    }
}
