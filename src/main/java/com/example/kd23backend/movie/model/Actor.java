package com.example.kd23backend.movie.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Actor {
    @Id
    private int id;

    private String name;

    @ManyToMany(mappedBy = "actors")
    @JsonBackReference(value = "movie-actor")
    private Set<Movie> movies;

}
