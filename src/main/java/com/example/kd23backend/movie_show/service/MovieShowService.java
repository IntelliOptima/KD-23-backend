package com.example.kd23backend.movie_show.service;

import com.example.kd23backend.movie_show.repository.MovieShowRepository;
import com.example.kd23backend.movie_show.model.MovieShow;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovieShowService implements IMovieShowService{

    private final MovieShowRepository movieShowRepository;

    public MovieShowService(MovieShowRepository movieShowRepository){
        this.movieShowRepository = movieShowRepository;
    }


    @Override
    public List<MovieShow> findAllByStartDateTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        LocalDateTime startofDay = startOfDay.with(LocalTime.MIN);
        LocalDateTime endofDay = endOfDay.with(LocalTime.MAX);
        return movieShowRepository.findAllByStartDateTimeBetween(startofDay, endofDay);
    }

    @Override
    public Optional<MovieShow> findMovieShowById(int id) {
        return movieShowRepository.findById(id);
    }
}