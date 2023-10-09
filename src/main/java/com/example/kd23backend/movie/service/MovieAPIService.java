package com.example.kd23backend.movie.service;

import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.repository.MovieRepository;
import com.example.kd23backend.movie.service.interfaces.IMovieAPIService;
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

@Service
public class MovieAPIService implements IMovieAPIService {

    final String BASE_MOVIE_API_URL = "https://api.themoviedb.org/3/movie/";
    final String API_KEY = "?api_key=bf45e26b2cbe79b9bcb399d646313e59&";
    final String APPEND_TO_MOVIE = "append_to_response=videos,credits";
    InputStream inputStream = getClass().getResourceAsStream("/movie_ids_10_03_2023.json");

    private final RestTemplate restTemplate;
    private final MovieRepository movieRepository;


    public MovieAPIService(RestTemplate restTemplate, MovieRepository movieRepository) {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
    }


    public void fetchAllMovies() {
        Set<Movie> movies = new HashSet<>();
        Set<Integer> existingMovies = movieRepository.findAllIds();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            skipFirstFourMoviesFromList(br);
            Movie movie;
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject json = new JSONObject(line);
                int movieId = json.getInt("id");

                if (movieId > 150000) {
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
            System.out.println("Next batch of movies has been added!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Movie fetchOneMovie(Integer id) {
        try {

            ResponseEntity<Movie> movieResponse = restTemplate.exchange(
                    baseURLWithID(id),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            return movieResponse.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    private void skipFirstFourMoviesFromList(BufferedReader br) throws IOException {
        for (int i = 0; i < 5; i++) {
            br.readLine();
        }
    }


    private String baseURLWithID(Integer id) {
        return BASE_MOVIE_API_URL + id + API_KEY + APPEND_TO_MOVIE;
    }
}
