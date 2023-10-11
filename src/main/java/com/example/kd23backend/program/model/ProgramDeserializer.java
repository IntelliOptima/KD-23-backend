package com.example.kd23backend.program.model;

import com.example.kd23backend.movie.model.Movie;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

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
    public Program deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode programNode =  jsonParser.getCodec().readTree(jsonParser);
        Program program = new Program();

        return program;
    }
}
