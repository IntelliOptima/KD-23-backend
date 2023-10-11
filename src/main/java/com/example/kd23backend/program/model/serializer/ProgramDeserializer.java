package com.example.kd23backend.program.model.serializer;

import com.example.kd23backend.cinema.model.Cinema;
import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.program.model.Program;
import com.example.kd23backend.theater.model.Theater;
import com.example.kd23backend.theater.model.util.TheaterManager;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class ProgramDeserializer extends StdDeserializer<Program> {

    // Constructors -----------------------------------
    public ProgramDeserializer() {
        this(null);
    }

    protected ProgramDeserializer(Class<?> vc) {
        super(vc);
    }

    // Serializer ---------------------------------------
    @Override
    public Program deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode programNode = jsonParser.getCodec().readTree(jsonParser);
        Program program = new Program();

        Cinema cinema = new Cinema();
        cinema.setId(programNode.path("cinemaId").asInt());

        program.setStartDate(OffsetDateTime.parse(programNode.path("startDate").textValue()).toLocalDate());
        program.setEndDate(OffsetDateTime.parse(programNode.path("endDate").textValue()).toLocalDate());
        program.setCinema(cinema);

        program.setMovieShows(getMovieShowFromJson(programNode, program));
        return program;
    }

    private Set<MovieShow> getMovieShowFromJson(JsonNode programNode, Program program) {
        return StreamSupport.stream(programNode.path("movieShows").spliterator(), false)
                .map(movieShowNode -> {
                    MovieShow movieShow = new MovieShow();
                    movieShow.setProgram(program); // Link the MovieShow to its parent Program
                    Movie movie = new Movie();
                    movie.setId(movieShowNode.path("movie").path("id").asInt());
                    movieShow.setMovie(movie);

                    Theater theater = TheaterManager.createTheater(movieShowNode.path("theater").path("type").textValue());
                    theater.setId(movieShowNode.path("theater").path("id").asInt());
                    System.out.println("This is THEATER ID" + theater.getId());
                    movieShow.setTheater(theater);

                    LocalDateTime startDateTime = OffsetDateTime.parse(
                            movieShowNode.path("startDateTime").textValue()).toLocalDateTime().plusHours(2);
                    movieShow.setStartDateTime(startDateTime);

                    movieShow.setPrice(movieShowNode.path("price").asInt());
                    return movieShow;
                }).collect(Collectors.toSet());
    }

}
