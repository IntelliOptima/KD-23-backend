package com.example.kd23backend.movie.model.dtoObjects;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MovieDTO extends StdDeserializer<Movie> {

    private int id;
    private boolean adult;
    private List<Genre> genres;
    private List<Actor> actors;
    private String overview;
    private String poster_path;
    private String trailer;
    private LocalDate release_date;
    private String title;
    private float vote_average;

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
        movie.setActors(getActors(movieNode.path("credits").path("cast")));

        return movie;
    }

    private Set<Actor> getActors(JsonNode actorsNode) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Actor> actors = new HashSet<>();
        int counter = 0;
        for (JsonNode actorNode : actorsNode) {
            Actor actor = new Actor();
            actor.setName(actorNode.path("name").textValue());
            actors.add(actor);
            if (counter > 4) break;
            ++counter;
        }
        return actors;
    }

}
