package com.example.kd23backend.movie.model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Actor {
    @Id
    private int id;

    private String name;

    @Transient
    @ManyToMany(mappedBy = "actors")
    @JsonBackReference(value = "actor-movies")
    private Set<Movie> movies;

}
