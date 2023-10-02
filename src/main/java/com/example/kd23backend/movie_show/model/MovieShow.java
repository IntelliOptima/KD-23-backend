package com.example.kd23backend.movie_show.model;

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
public class MovieShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime startDateTime;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @JsonManagedReference
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id", referencedColumnName = "id")
    @JsonManagedReference
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    @JsonBackReference
    private Program program;
}
