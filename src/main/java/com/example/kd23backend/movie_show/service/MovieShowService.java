package com.example.kd23backend.movie_show.service;

import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.movie_show.repository.MovieShowRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

public class MovieShowService {
    private final MovieShowRepository movieShowRepository;

    public MovieShowService(MovieShowRepository movieShowRepository) {
        this.movieShowRepository = movieShowRepository;
    }


    @GetMapping("getShowsByDate/{date}")
    public List<MovieShow> getMovieShowByDate(@RequestBody LocalDate date) {
        return movieShowRepository.getMovieShowByDate(date);
    }

}
