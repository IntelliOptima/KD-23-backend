package com.example.kd23backend.movie.service;

import com.example.kd23backend.mappers.movie.MovieDTO;
import com.example.kd23backend.mappers.movie.MovieMapper;
import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Set;

@Service
public class MovieAPIService implements IMovieAPIService {
    final String apiKey = "?api_key=bf45e26b2cbe79b9bcb399d646313e59";
    final String appendToMovie = "append_to_response=videos,images,credits";
    final String BASE_MOVIE_API_URL = "https://api.themoviedb.org/3/movie/";
    final String bearer = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiZjQ1ZTI2YjJjYmU3OWI5YmNiMzk5ZDY0NjMxM2U1OSIsInN";

    private final MovieService movieService;
    private final RestTemplate restTemplate;
    private final MovieMapper movieMapper;


    public MovieAPIService(MovieService movieService, RestTemplate restTemplate, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.restTemplate = restTemplate;
        this.movieMapper = movieMapper;
    }

    @Override
    public void fetchMovies() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Authorization", bearer)
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                })
                .build();

        int pageNumber = 1;
        boolean hasMoreMovies = true;

        while (hasMoreMovies) {
            String url = constructUrl(pageNumber);
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                hasMoreMovies = hasMovieList_Still_NewMovies(response);
                if (hasMoreMovies) {
                    // Process the response here, save movies or whatever you want to do
                    // ...

                    // increment the page number for the next iteration
                    pageNumber++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String constructUrl(int pageNumber) {
        return BASE_MOVIE_API_URL + pageNumber + apiKey + appendToMovie;
    }

    public boolean hasMovieList_Still_NewMovies(Response response) {
        return response.code() != 400;
    }

    public Movie convertDTO_ToEntity(MovieDTO movieDTO) {
        return movieMapper.dtoToEntity(movieDTO);
    }


    public Set<Genre> setGenreForMovie(Genre[] genres_id) {
        return movieMapper.mapGenreIdsToGenreSet(genres_id);
    }

    public Set<Actor> setActorsForMovie(Actor[] actors) {
        return movieMapper.mapActorsFromFetchToSetActors(actors);
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
