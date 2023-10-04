package com.example.kd23backend.movie.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Actor {
    @Id
    @JsonProperty
    private String name;

    @ManyToMany(mappedBy = "actors")
    @JsonBackReference(value = "movie-actor")
    private Set<Movie> movies;

}
