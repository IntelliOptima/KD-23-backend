package com.example.kd23backend.movie.model;


import com.example.kd23backend.movie.model.dtoObjects.ActorDTO;
import com.example.kd23backend.movie.model.dtoObjects.MovieDTO;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonSerialize(using = ActorSerializer.class)
public class Actor {
    @Id
    private int id;

    private String name;

    @ManyToMany(mappedBy = "actors")
    @JsonBackReference(value = "movie-actor")
    private Set<Movie> movies;

}
