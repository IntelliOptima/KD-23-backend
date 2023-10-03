package com.example.kd23backend.movie.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Actor {

    @Id
    @JsonProperty
    private String name;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.MERGE)
    @JsonBackReference
    private Set<Movie> movies;
}
