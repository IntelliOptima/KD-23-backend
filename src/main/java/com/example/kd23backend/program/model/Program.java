package com.example.kd23backend.program.model;

import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.show.model.Show;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    @JsonManagedReference
    private Set<Show> shows;
}
