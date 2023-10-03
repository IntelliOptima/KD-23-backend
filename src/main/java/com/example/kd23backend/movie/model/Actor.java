package com.example.kd23backend.movie.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Set;

@Entity
@Data
public class Actor {

    @Id
    private String name;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.MERGE)
    @JsonBackReference
    private Set<Movie> movies;
}
