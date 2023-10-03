package com.example.kd23backend.movie.service;

import com.example.kd23backend.movie.mappers.MovieDTO;
import com.example.kd23backend.movie.mappers.MovieMapper;
import com.example.kd23backend.movie.model.Movie;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
public class MovieAPIService implements IMovieAPIService {

    String apiTest = "https://api.themoviedb.org/3/movie/18?api_key=bf45e26b2cbe79b9bcb399d646313e59&append_to_response=videos,credits";
    final String apiKey = "?api_key=bf45e26b2cbe79b9bcb399d646313e59";
    final String appendToMovie = "append_to_response=videos,images,credits";
    final String BASE_MOVIE_API_URL = "https://api.themoviedb.org/3/movie/";
    final String bearer = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiZjQ1ZTI2YjJjYmU3OWI5YmNiMzk5ZDY0NjMxM2U1OSIsInN";

    private final RestTemplate restTemplate;

    @Autowired
    MovieMapper movieMapper;

    public MovieAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void fetchOneMovie() {
        ResponseEntity<MovieDTO> movieDTOResponse = restTemplate.exchange(
                apiTest,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<MovieDTO>() {}
        );

        Movie myMovie = movieMapper.dtoToEntity(movieDTOResponse.getBody());
        System.out.println(movieDTOResponse.getBody());
        System.out.println(myMovie.getGenres().toArray()[0]);
        System.out.println(myMovie.getActors().toArray()[0]);
        System.out.println(myMovie.getTrailer());
    }



    @Override
    public void fetchMovies() {
    }

    @Override
    public void fetchActors_And_AddToMovies(Set<Movie> movies) {

    }

    @Override
    public void fetchTrailers_And_AddToMovies(Set<Movie> movies) {

    }

    @Override
    public void saveAllMovies(Set<Movie> movies) {

    }
}
