package com.example.kd23backend.movie.model.dtoObjects;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
public class MovieDTO extends StdDeserializer<Movie> {

    private int id;
    private boolean adult;
    private Set<Genre> genres;
    private Set<Actor> actors;
    private String overview;
    private String poster_path;
    private String trailer;
    private LocalDate release_date;
    private String title;
    private double vote_average;

    // Constructors -----------------------------------
    public MovieDTO() {
        this(null);
    }
    protected MovieDTO(Class<?> vc) {
        super(vc);
    }

    // Serializer ---------------------------------------
    @Override
    public Movie deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode movieNode =  jsonParser.getCodec().readTree(jsonParser);
        Movie movie = new Movie();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        movie.setId(movieNode.path("id").asInt());
        movie.setTitle(movieNode.path("title").textValue());
        movie.setReleaseDate(LocalDate.parse(movieNode.path("release_date").textValue(), formatter));
        movie.setRuntime(movieNode.path("runtime").asInt());
        movie.setDescription(movieNode.path("overview").textValue());
        movie.setAdult(movieNode.path("adult").asBoolean());
        movie.setPoster("https://image.tmdb.org/t/p/w500/" + movieNode.path("poster_path").textValue());
        movie.setVoteRating(movieNode.path("vote_average").asDouble());
        movie.setGenres(getGenres(movieNode.path("genres")));
        movie.setActors(getActors(movieNode.path("credits").path("cast")));
        movie.setTrailer(getTrailer(movieNode.path("videos").path("results")));

        return movie;
    }

    private Set<Actor> getActors(JsonNode actorsNode) throws JsonProcessingException {
        return StreamSupport.stream(actorsNode.spliterator(), false)
                .map(actorNode -> {
                    Actor actor = new Actor();
                    actor.setName(actorNode.path("name").textValue());
                    return actor;
                }).limit(5).collect(Collectors.toSet());
    }

    private Set<Genre> getGenres(JsonNode genresNode) {
        return StreamSupport.stream(genresNode.spliterator(), false)
                .map(genreNode -> {
                    Genre genre = new Genre();
                    genre.setId(genreNode.path("id").asInt());
                    genre.setName(genreNode.path("name").textValue());
                    return genre;
                })
                .collect(Collectors.toSet());
    }

    private String getTrailer(JsonNode trailersNode) {
       List<JsonNode> searchKeys = StreamSupport.stream(trailersNode.spliterator(), false)
                .filter(trailerNode -> trailerNode.path("site").textValue().equals("YouTube") &&
                 trailerNode.path("type").textValue().equals("Trailer"))
                .toList();

        return !searchKeys.isEmpty() ?
                "https://www.youtube.com/watch?v=" + searchKeys.get(0).path("key").textValue() :
                "movie has no trailer";
    }
}
