package com.example.kd23backend.movie.service;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.repository.ActorRepository;
import com.example.kd23backend.movie.repository.GenreRepository;
import com.example.kd23backend.movie.repository.MovieRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@Service
public class MovieAPIService implements IMovieAPIService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    final String BASE_MOVIE_API_URL = "https://api.themoviedb.org/3/movie/";
    final String API_KEY = "?api_key=bf45e26b2cbe79b9bcb399d646313e59&";
    final String APPEND_TO_MOVIE = "append_to_response=videos,credits";
    final String BEARER = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiZjQ1ZTI2YjJjYmU3OWI5YmNiMzk5ZDY0NjMxM2U1OSIsInN1YiI6IjY1MTNlZDg0Y2FkYjZiMDJiZTU0OWYwNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VeSYNMtKMSrQskLEz_ZNU6f4p160eIGRYaB7m31hUHM\"";

    private final RestTemplate restTemplate;

    public MovieAPIService(RestTemplate restTemplate, MovieRepository movieRepository, ActorRepository actorRepository, GenreRepository genreRepository) {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
    }

    public void fetchAllMovies() {
        Set<Movie> movies = new HashSet<>();
        Set<Actor> actors = new HashSet<>();

        List<Boolean> invalidMovies = new ArrayList<>();
        Integer i = 1;

        Movie movie = fetchOneMovie(i);

        while (invalidMovies.size() < 50 && i <= 100) {
            if (movie == null || movie.getTitle().isEmpty()) {
                invalidMovies.add(false);
            } else {
                movie.getActors().forEach(actor -> {
                    if (!actorRepository.existsById(actor.getName())) {
                        actorRepository.save(actor);
                    }
                });

                movie.getGenres().forEach(genre -> {
                    if (!genreRepository.existsById(genre.getId())) {
                        genreRepository.save(genre);
                    }
                });
                movieRepository.save(movie);
                invalidMovies.clear();
            }

            i++;
            System.out.println(i);
            movie = fetchOneMovie(i);
        }
    }


    public Movie fetchOneMovie(Integer id) {
        try {

            ResponseEntity<Movie> movieResponse = restTemplate.exchange(
                    baseURLWithID(id),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Movie>() {
                    }
            );

            return movieResponse.getBody();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }


    public void saveAllMovies() {
        /*
        for (Movie movie : fetchAllMovies()) {
            Movie existingMovie = movieRepository.findById(movie.getId()).orElse(null);

            if (existingMovie != null) {
                // Handle actors
                for (Actor actor : movie.getActors()) {
                    existingMovie.getActors().add(actor);
                }
                movieRepository.save(existingMovie);
            } else {
                movieRepository.save(movie);
            }
        }
         */
    }




    // --------------------------
    public Optional<Movie> findMovieWithActorsAndGenresById(Integer id) {
        return movieRepository.findByIdWithActorsAndGenres(id);
    }

    private String baseURLWithID(Integer id) {
        return BASE_MOVIE_API_URL + id + API_KEY + APPEND_TO_MOVIE;
    }
}
