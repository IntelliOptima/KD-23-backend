package com.example.kd23backend.movie.controller;

import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.service.IMovieService;
import com.example.kd23backend.movie.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/movie")
public class MovieController {
    private IMovieService movieService;
    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> movies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie movie(@PathVariable("id") Integer id) {
        return movieService.getSpecificMovie(id);
    }

    @GetMapping("/{title}")
    public List<Movie> findAllByTitle(@PathVariable("title") String title) {
        return movieService.findAllByTitle(title);
    }

    @GetMapping("/{release-date}")
    public List<Movie> findByReleaseDate(@PathVariable("release-date") LocalDate releaseDate) {
        return movieService.findByReleaseDate(releaseDate);
    }

    @GetMapping("/adult-true")
    public List<Movie> findAllByIsAdultTrue() {
        return movieService.findAllByIsAdultTrue();
    }
    @GetMapping("/adult-false")
    public List<Movie> findAllByIsAdultFalse() {
        return movieService.findAllByIsAdultFalse();
    }

    @GetMapping("/has-poster")
    public List<Movie> findAllByPosterIsNotEmpty() {
        return movieService.findAllByPosterIsNot("movie has no trailer");
    }

    @GetMapping("/{runtime}")
    public List<Movie> findAllByRuntimeLessThan(@PathVariable("runtime") Integer runtime) {
        return findAllByRuntimeLessThan(runtime);
    }

    @RequestMapping("{vote-rating}")
    public List<Movie> findAllByVoteRatingIsGreaterThan(@PathVariable("vote-rating") Double voteRating) {
        return movieService.findAllByVoteRatingIsGreaterThan(voteRating);
    }
}
