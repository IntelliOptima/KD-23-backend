package com.example.kd23backend.movie.service;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.repository.ActorRepository;
import com.example.kd23backend.movie.repository.GenreRepository;
import com.example.kd23backend.movie.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService, IGenreService, IActorService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return moviePage.getContent();
    }

    @Override
    public Optional<Movie> getSpecificMovie(Integer id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAllByTitleContains(String title) {
        return movieRepository.findAllByTitleContainsIgnoreCase(title);
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findAllByActorsContaining(Actor actor) {
        return movieRepository.findAllByActorsContaining(actor);
    }

    @Override
    public List<Movie> findAllByGenresContaining(Genre genre) {
        return movieRepository.findAllByGenresContaining(genre);
    }


    @Override
    public List<Movie> findByReleaseDate(LocalDate releaseDate) {
        return movieRepository.findByReleaseDate(releaseDate);
    }

    @Override
    public List<Movie> findAllByIsAdultTrue() {
        return movieRepository.findAllByIsAdultTrue();
    }

    @Override
    public List<Movie> findAllByIsAdultFalse() {
        return movieRepository.findAllByIsAdultFalse();
    }

    @Override
    public List<Movie> findAllByPosterIsNot(String poster) {
        return movieRepository.findAllByPosterIsNot(poster);
    }

    @Override
    public List<Movie> findAllByTrailerIsNot(String trailer) {
        return movieRepository.findAllByTrailerIsNot(trailer);
    }

    @Override
    public List<Movie> findAllByRuntimeLessThan(Integer runtime) {
        return movieRepository.findAllByRuntimeLessThan(runtime);
    }

    @Override
    public List<Movie> findAllByVoteRatingIsGreaterThan(Double voteRating) {
        return movieRepository.findAllByVoteRatingIsGreaterThan(voteRating);
    }

    @Override
    public Genre findGenreById(int genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    public List<Genre> findGenreByNameContainingIgnoreCase(String genreName) {
        return genreRepository.findByNameContainingIgnoreCase(genreName);
    }

    @Override
    public List<Genre> findAllGenresByMoviesContaining(Integer movieId) {
        return genreRepository.findAllByMoviesContaining(movieId);
    }

    @Override
    public List<Actor> findActorByNameContainingIgnoreCase(String actorName) {
        return actorRepository.findByNameContainingIgnoreCase(actorName);
    }

    @Override
    public List<Actor> findAllActorsByMoviesContaining(Integer movieId) {
        return actorRepository.findAllByMoviesContaining(movieId);
    }
}
