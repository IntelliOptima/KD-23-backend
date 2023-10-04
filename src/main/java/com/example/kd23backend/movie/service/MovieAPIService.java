package com.example.kd23backend.movie.service;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.repository.ActorRepository;
import com.example.kd23backend.movie.repository.GenreRepository;
import com.example.kd23backend.movie.repository.MovieRepository;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieAPIService implements IMovieAPIService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    final String BASE_MOVIE_API_URL = "https://api.themoviedb.org/3/movie/";
    final String API_KEY = "?api_key=bf45e26b2cbe79b9bcb399d646313e59&";
    final String APPEND_TO_MOVIE = "append_to_response=videos,credits";
    final String BEARER = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiZjQ1ZTI2YjJjYmU3OWI5YmNiMzk5ZDY0NjMxM2U1OSIsInN1YiI6IjY1MTNlZDg0Y2FkYjZiMDJiZTU0OWYwNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VeSYNMtKMSrQskLEz_ZNU6f4p160eIGRYaB7m31hUHM\"";
    InputStream inputStream = getClass().getResourceAsStream("/movie_ids_10_03_2023.json");
    private final RestTemplate restTemplate;

    public MovieAPIService(RestTemplate restTemplate, MovieRepository movieRepository, ActorRepository actorRepository, GenreRepository genreRepository, ActorRepository actorRepository1) {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository1;
    }


    public void fetchAllMovies() {
        Set<Movie> movies = new HashSet<>();
        Set<Integer> existingMovies = movieRepository.findAllIds();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            skipFirstFourMovies(br);
            Movie movie;
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject json = new JSONObject(line);
                int movieId = json.getInt("id");

                if (movieId > 20000) {
                    break;
                }

                if (!existingMovies.contains(movieId)) {
                    movie = fetchOneMovie(movieId);
                    movies.add(movie);
                    System.out.println("Movie: " + movieId + " has been added!");
                } else {
                    System.out.println("Movie: " + movieId + " already in the Database!");
                }

            }
            movieRepository.saveAll(movies);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void skipFirstFourMovies(BufferedReader br) throws IOException {
        for (int i = 0; i < 5; i++) {
            br.readLine();
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


    private String baseURLWithID(Integer id) {
        return BASE_MOVIE_API_URL + id + API_KEY + APPEND_TO_MOVIE;
    }
}
