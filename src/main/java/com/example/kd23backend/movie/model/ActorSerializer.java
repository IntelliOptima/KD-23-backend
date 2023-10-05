package com.example.kd23backend.movie.model;

import com.example.kd23backend.movie.model.dtoObjects.ActorDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ActorSerializer extends StdSerializer<Actor> {
    public ActorSerializer() {
        this(null);
    }

    public ActorSerializer(Class<Actor> t) {
        super(t);
    }
    @Override
    public void serialize(Actor actor, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setId(actor.getId());
        actorDTO.setName(actor.getName());

        // Serialize the DTO object
        jsonGenerator.writeObject(actorDTO);
    }
}
