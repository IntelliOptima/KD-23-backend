package com.example.kd23backend.movie.controller;

import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.service.IMovieService;
import com.example.kd23backend.movie.service.MovieService;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/movie")
public class MovieController {
    private final IMovieService movieService;
    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable("id") Integer id) {
        Optional<Movie> movie = movieService.getSpecificMovie(id);
        return movie.isPresent() ? ResponseEntity.ok(movie.get()) : new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/where-title/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable("title") String title) {
        Optional<Movie> movie = movieService.findByTitle(title);
        return movie.isPresent() ?  ResponseEntity.ok(movie.get()) : new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all-where-title-contains/{title}")
    public ResponseEntity<List<Movie>> getAllMoviesByTitle(@PathVariable("title") String title) {
        List<Movie> movies = movieService.findAllByTitleContains(title);
        return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
    }

//    @RequestMapping("/all-where-genre-contains/{genre}")
//    public ResponseEntity<List<Movie>> getAllMoviesByGenresContains(String genre) {
//
//        List<Movie> movies = movieService.findAllByGenresContaining(genre);
//        return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
//    }
//
//    @RequestMapping("/all-where-actor-contains/{actor}")
//    public ResponseEntity<List<Movie>> getAllMoviesByActorsContains(String actor) {
//
//        List<Movie> movies = movieService.findAllByActorsContaining(actor);
//        return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
//    }

    @GetMapping("/release-date/{release-date}")
    public ResponseEntity<List<Movie>> getMoviesByReleaseDate(@PathVariable("release-date") String releaseDateStr) {
        try {
            LocalDate releaseDate = LocalDate.parse(releaseDateStr);
            List<Movie> movies = movieService.findByReleaseDate(releaseDate);

            return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/adult-true")
    public ResponseEntity<List<Movie>> getMoviesWhereAdultTrue() {
        List<Movie> movies = movieService.findAllByIsAdultTrue();
        return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
    }
    @GetMapping("/adult-false")
    public ResponseEntity<List<Movie>> getMoviesWhereAdultFalse() {
        List<Movie> movies = movieService.findAllByIsAdultFalse();
        return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
    }

    @GetMapping("/with-poster")
    public ResponseEntity<List<Movie>> getMoviesWithPoster() {
        List<Movie> movies = movieService.findAllByPosterIsNot("movie has no poster");
        return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
    }

    @GetMapping("/with-trailer")
    public ResponseEntity<List<Movie>> gteMoviesWithTrailer() {
        List<Movie> movies = movieService.findAllByTrailerIsNot("movie has no trailer");
        return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
    }

    @GetMapping("/runtime-less-than/{runtime}")
    public ResponseEntity<List<Movie>> getMoviesWhereRuntimeLessThan(@PathVariable("runtime") Integer runtime) {
        if (runtime == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        List<Movie> movies = movieService.findAllByRuntimeLessThan(runtime);
        return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
    }

    @RequestMapping("/vote-rating-greater-than/{vote-rating}")
    public ResponseEntity<List<Movie>> getMoviesWhereVoteRatingIsGreaterThan(@PathVariable("vote-rating") Double voteRating) {
        if (voteRating == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        List<Movie> movies = movieService.findAllByVoteRatingIsGreaterThan(voteRating);
        return movies.isEmpty() ? new ResponseEntity(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(movies);
    }
}
