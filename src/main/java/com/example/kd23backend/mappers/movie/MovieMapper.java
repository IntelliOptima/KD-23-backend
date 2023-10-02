package com.example.kd23backend.mappers.movie;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.stream.Collectors;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "releaseYear", expression = "java( Year.of(movie.getRelease_date().getYear()) )")
    @Mapping(target = "poster", source = "poster_path")
    @Mapping(target = "description", source = "overview")
    @Mapping(target = "genres", expression = "java( mapGenreIdsToGenreSet(movie.getGenre_ids()) )")
    Movie dtoToEntity(MovieDTO movie);

    default Set<Genre> mapGenreIdsToGenreSet(Genre[] genre_ids) {
        Set<Genre> genres = new HashSet<>();
        if (genre_ids != null) {
            // You would have a service call to get the name,
            // for example, genre.setName(genreService.getNameById(genre.getId()));
            genres.addAll(Set.of(genre_ids));
        }
        return genres;
    }

    default Set<Actor> mapActorsFromFetchToSetActors(Actor[] actorForMovie) {
        Set<Actor> actors = new HashSet<>();
        if (actorForMovie != null && !(actorForMovie.length < 1)) {
            //TODO implement logic for mapping over actors and add to Set<Actors> actors
            actors.addAll(Set.of(actorForMovie));
        }
        return actors;
    }

    default Set<Movie> setTrailerForMovies(Set<Movie> movies, Map<Integer, String> trailers) {
        return movies.stream().peek(movie -> {
            String trailer = trailers.get(movie.getId());
            if (trailer != null) {
                movie.setTrailer(trailer);
            }
        }).collect(Collectors.toSet());
    }
}
