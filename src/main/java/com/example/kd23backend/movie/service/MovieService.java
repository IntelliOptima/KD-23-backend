package com.example.kd23backend.movie.service;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.repository.ActorRepository;
import com.example.kd23backend.movie.repository.GenreRepository;
import com.example.kd23backend.movie.repository.MovieRepository;
import com.example.kd23backend.movie.service.interfaces.IMovieService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Movie> getAllMovies(Pageable pageable) {
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return moviePage.getContent();
    }

    @Override
    public Optional<Movie> getSpecificMovie(Integer id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAllByTitleContains(String title, Pageable pageable) {
        return movieRepository.findAllByTitleContainsIgnoreCaseOrderByTitle(title, pageable);
    }

    @Override
    public List<Movie> findAllByActorsContaining(Actor actor, Pageable pageable) {
        return movieRepository.findAllByActorsContainingIgnoreCaseOrderByTitle(actor, pageable);
    }

    @Override
    public List<Movie> findAllByGenresContaining(Genre genre, Pageable pageable) {
        return movieRepository.findAllByGenresContainingIgnoreCaseOrderByTitle(genre, pageable);
    }


    @Override
    public List<Movie> findByReleaseDate(LocalDate releaseDate, Pageable pageable) {
        return movieRepository.findByReleaseDateOrderByReleaseDate(releaseDate, pageable);
    }

    @Override
    public List<Movie> findAllByIsAdultTrue(Pageable pageable) {
        return movieRepository.findAllByIsAdultTrue(pageable);
    }

    @Override
    public List<Movie> findAllByIsAdultFalse(Pageable pageable) {
        return movieRepository.findAllByIsAdultFalseOrderByTitle(pageable);
    }

    @Override
    public List<Movie> findAllByPosterIsNot(String poster, Pageable pageable) {
        return movieRepository.findAllByPosterIsNotOrderByTitle(poster, pageable);
    }

    @Override
    public List<Movie> findAllByTrailerIsNot(String trailer, Pageable pageable) {
        return movieRepository.findAllByTrailerIsNotOrderByTitle(trailer, pageable);
    }

    @Override
    public List<Movie> findAllByRuntimeLessThan(Integer runtime, Pageable pageable) {
        return movieRepository.findAllByRuntimeLessThanOrderByRuntimeDesc(runtime, pageable);
    }

    @Override
    public List<Movie> findAllByVoteRatingIsGreaterThan(Double voteRating, Pageable pageable) {
        return movieRepository.findAllByVoteRatingIsGreaterThanOrderByVoteRating(voteRating, pageable);
    }

    @Override
    public Genre findGenreById(int genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    public List<Genre> findGenreByNameContainingIgnoreCase(String genreName) {
        return genreRepository.findByNameContainingIgnoreCaseOrderByName(genreName);
    }

    @Override
    public List<Genre> findAllGenresByMoviesContaining(Integer movieId, Pageable pageable) {
        return genreRepository.findAllByMoviesContaining(movieId, pageable);
    }

    @Override
    public List<Actor> findActorByNameContainingIgnoreCase(String actorName) {
        return actorRepository.findByNameContainingIgnoreCase(actorName);
    }

    @Override
    public List<Actor> findAllActorsByMoviesContaining(Integer movieId, Pageable pageable) {
        return actorRepository.findAllByMoviesContaining(movieId, pageable);
    }
}
