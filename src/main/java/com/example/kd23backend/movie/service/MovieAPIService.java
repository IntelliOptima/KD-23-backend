package com.example.kd23backend.movie.service;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.repository.ActorRepository;
import com.example.kd23backend.movie.repository.GenreRepository;
import com.example.kd23backend.movie.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    InputStream inputStream = getClass().getResourceAsStream("/movie_ids_10_03_2023.json");
    private final RestTemplate restTemplate;

    public MovieAPIService(RestTemplate restTemplate, MovieRepository movieRepository, ActorRepository actorRepository, GenreRepository genreRepository) {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    public void fetchAllMovies() {
        List<Movie> movies = new ArrayList<>();
        List<Actor> actors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        Set<Integer> existingMoviesIds = movieRepository.findAllIds();
        Set<String> existingActorNames = actorRepository.findAllNames();
        Set<Integer> existingGenreIds = genreRepository.findAllIds();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));) {
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject json = new JSONObject(line);
                int movieId = json.getInt("id");

                if (movieId < 7500) {
                    continue;
                }

                if (movieId == 20000) {
                    break;
                }
                Movie movie = fetchOneMovie(movieId);

                for (Actor actor : movie.getActors()) {
                    if (!existingActorNames.contains(actor.getName())) {
                        actors.add(actor);
                        existingActorNames.add(actor.getName());
                    }
                }
                for (Genre genre : movie.getGenres()) {
                    if (!existingGenreIds.contains(genre.getId())) {
                        genres.add(genre);
                        existingGenreIds.add(genre.getId());
                    }
                }

                if (!existingMoviesIds.contains(movie.getId())) {
                    movies.add(movie);
                    existingMoviesIds.add(movieId);
                }

                System.out.println("Movie: " + movieId + " has been added!");
            }
            actorRepository.saveAll(actors);
            genreRepository.saveAll(genres);
            movieRepository.saveAll(movies);


        } catch (Exception e) {
            e.printStackTrace();
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

    private String baseURLWithID(Integer id) {
        return BASE_MOVIE_API_URL + id + API_KEY + APPEND_TO_MOVIE;
    }
}
