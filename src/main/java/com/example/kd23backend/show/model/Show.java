package com.example.kd23backend.show.model;

import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.program.model.Program;
import com.example.kd23backend.theater.model.Theater;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne
    @JsonManagedReference
    private Theater theater;

    private LocalDateTime startDateTime;
    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    @JsonBackReference
    private Program program;
}
