package com.example.kd23backend.movie.mappers;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "releaseDate", source = "release_date")
    @Mapping(target = "poster", source = "poster_path")
    @Mapping(target = "description", source = "overview")
    @Mapping(target = "voteRating", source = "vote_average")
    @Mapping(target = "genres", expression = "java( mapGenres_ToGenreSet(movie.getGenres()) )")
    @Mapping(target = "actors", expression = "java ( mapActors_To_ActorsSet(movie.getActors()) )")
    @Mapping(target = "trailer", expression = "java ( mapTrailer_To_TrailerPath(movie.getVideos().getResults()) )")
    Movie dtoToEntity(MovieDTO movie);


    default Set<Genre> mapGenres_To_GenreSet(List<Genre> receivedGenres) {
        Set<Genre> genres = new HashSet<>();
        if (receivedGenres != null) {
            genres.addAll(receivedGenres);
            System.out.println(genres);
        }
        return genres;
    }

    default Set<Actor> mapActors_To_ActorsSet(List<Actor> receivedActors) {
        Set<Actor> actors = new HashSet<>();
        if (receivedActors != null && !(receivedActors.isEmpty())) {
            actors.addAll(receivedActors.subList(0, 5));
            System.out.println("HECK YIR HER ER ACTOR: " + actors);
        }
        return actors;
    }

    default String mapTrailer_To_TrailerPath(ResultDTO receivedTrailers) {
        TrailerDTO trailerDTO = receivedTrailers.getTrailers().stream()
                .filter(trailer -> trailer.getSite().equals("YouTube") && trailer.getType().equals("Trailer"))
                .findFirst().orElse(null);
        return trailerDTO != null ? "www.youtube.com/watch?v=" + trailerDTO.getKey() : "No Trailer Available";
    }
}
