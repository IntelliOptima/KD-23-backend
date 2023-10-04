package com.example.kd23backend.movie.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name")
@Entity
@Data
@NoArgsConstructor
public class Actor {

    @Id
    private String name;

    @ManyToMany(mappedBy = "actors")
    @JsonBackReference
    private Set<Movie> movies;
}
