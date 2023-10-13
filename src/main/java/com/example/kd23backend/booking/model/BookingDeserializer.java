package com.example.kd23backend.booking.model;

import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.seat.model.Seat;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookingDeserializer extends StdDeserializer<Booking> {

    // Constructors -----------------------------------
    public BookingDeserializer() {
        this(null);
    }
    protected BookingDeserializer(Class<?> vc) {
        super(vc);
    }

    // Serializer ---------------------------------------
    @Override
    public Booking deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode bookingNode =  jsonParser.getCodec().readTree(jsonParser);
        Booking booking = new Booking();
        booking.setEmail(bookingNode.path("email").textValue());
        MovieShow movieShow = new MovieShow();
        movieShow.setId(bookingNode.path("moviewShowId").asInt());
        booking.setMovieShow(movieShow);
        Seat seat = new Seat();
        seat.setId(bookingNode.path("seatId").asInt());
        booking.setSeat(seat);

        return booking;
    }
}
