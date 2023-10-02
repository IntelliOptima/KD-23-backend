package com.example.kd23backend.movie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Genre {

    @Id
    private int id;

    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
    private Set<Movie> movies;
}
