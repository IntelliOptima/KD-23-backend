package com.example.kd23backend.movie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Genre {

    @Id
    private Integer id;

    private String name;

    @Transient
    @ManyToMany(mappedBy = "genres")
    @JsonBackReference(value = "genre-movie")
    private Set<Movie> movies;
}

