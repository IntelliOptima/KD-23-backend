package com.example.kd23backend.movie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Genre {

    @Id
    private Integer id; //API defines ID

    private String name;

    @ManyToMany(mappedBy = "genres", cascade = CascadeType.MERGE)
    @JsonBackReference
    private Set<Movie> movies;
}
