package com.example.kd23backend.movie.controller;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.service.interfaces.IMovieService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/movie/")
public class MovieController {
    private final int AMOUNT_OF_MOVIES = 15;
    private final int AMOUNT_OF_ACTORS = 20;

    private final IMovieService movieService;
    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("page=/{page}")
    public ResponseEntity<List<Movie>> getMovies(@PathVariable int page) {
        List<Movie> movies = movieService.getAllMovies(getPageableRequest(page, AMOUNT_OF_MOVIES));
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @GetMapping("id=/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable("id") Integer id) {
        Optional<Movie> movie = movieService.getSpecificMovie(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("where-movie-title-contains=/{title}/page=/{page}")
    public ResponseEntity<List<Movie>> getAllMoviesByTitle(@PathVariable("title") String title,
                                                           @PathVariable("page") int page) {
        List<Movie> movies = movieService.findAllByTitleContains(title, getPageableRequest(page, AMOUNT_OF_MOVIES));
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @RequestMapping("where-movie-genre-contains=/{genre}/page=/{page}")
    public ResponseEntity<List<Movie>> getAllMoviesByGenresContains(
            @PathVariable("genre") String genreName, @PathVariable("page") int page) {
        Genre genre = movieService.findGenreByNameContainingIgnoreCase(genreName).get(0);
        List<Movie> movies = movieService.findAllByGenresContaining(genre, getPageableRequest(page, AMOUNT_OF_MOVIES));
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @RequestMapping("where-movie-actor-contains/{actor}/page=/{page}")
    public ResponseEntity<List<Movie>> getAllMoviesByActorsContains(
            @PathVariable("actor") String actorName, @PathVariable("page") int page) {
        List<Actor> actor = movieService.findActorByNameContainingIgnoreCase(
                actorName, getPageableRequest(page, AMOUNT_OF_ACTORS));

        if (actor.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Movie> movies = movieService.findAllByActorsContaining(
                actor.get(0), getPageableRequest(page, AMOUNT_OF_MOVIES));
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @GetMapping("release-date=/{release-date}/page=/{page}")
    public ResponseEntity<List<Movie>> getMoviesByReleaseDate(
            @PathVariable("release-date") String releaseDateStr,
            @PathVariable("page") int page) {
        try {
            LocalDate releaseDate = LocalDate.parse(releaseDateStr);
            List<Movie> movies = movieService.findByReleaseDate(
                    releaseDate, getPageableRequest(page, AMOUNT_OF_MOVIES));

            return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("adult-true/page=/{page}")
    public ResponseEntity<List<Movie>> getMoviesWhereAdultTrue(@PathVariable int page) {
        List<Movie> movies = movieService.findAllByIsAdultTrue(getPageableRequest(page, AMOUNT_OF_MOVIES));
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }
    @GetMapping("adult-false/page=/{page}")
    public ResponseEntity<List<Movie>> getMoviesWhereAdultFalse(@PathVariable int page) {
        List<Movie> movies = movieService.findAllByIsAdultFalse(getPageableRequest(page, AMOUNT_OF_MOVIES));
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @GetMapping("with-poster/page=/{page}")
    public ResponseEntity<List<Movie>> getMoviesWithPoster(@PathVariable int page) {
        List<Movie> movies = movieService.findAllByPosterIsNot("movie has no poster", getPageableRequest(page, AMOUNT_OF_MOVIES));
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @GetMapping("with-trailer/page=/{page}")
    public ResponseEntity<List<Movie>> gteMoviesWithTrailer(@PathVariable int page) {
        List<Movie> movies = movieService.findAllByTrailerIsNot("movie has no trailer",
                getPageableRequest(page, AMOUNT_OF_MOVIES));
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @GetMapping("runtime-less-than/{runtime}/page=/{page}")
    public ResponseEntity<List<Movie>> getMoviesWhereRuntimeLessThan(@PathVariable("runtime") Integer runtime, @PathVariable int page) {
        if (runtime == null || runtime > 500) {
            return ResponseEntity.badRequest().build();
        }

        List<Movie> movies = movieService.findAllByRuntimeLessThan(runtime, getPageableRequest(page, AMOUNT_OF_MOVIES));
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @RequestMapping("vote-rating-greater-than/{vote-rating}/page=/{page}")
    public ResponseEntity<List<Movie>> getMoviesWhereVoteRatingIsGreaterThan(
            @PathVariable("vote-rating") Double voteRating,
            @PathVariable int page) {
        if (voteRating == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<Movie> movies = movieService.findAllByVoteRatingIsGreaterThan(voteRating, getPageableRequest(page, AMOUNT_OF_MOVIES));
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    private Pageable getPageableRequest(int page, int amount) {
        return PageRequest.of(page, amount);
    }
}
