package com.example.kd23backend.movie.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Actor {

    @Id
    private String name;

    @ManyToMany(mappedBy = "actors")
    @JsonBackReference
    private Set<Movie> movies;
}
