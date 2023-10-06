package com.example.kd23backend.movie_show.model;

import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.program.model.Program;
import com.example.kd23backend.theater.model.Theater;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class MovieShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime startDateTime;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    //@JsonManagedReference(value = "movieshow-movie")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id", referencedColumnName = "id")
    //@JsonManagedReference(value = "movieshow-theater")
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    @JsonBackReference(value = "movieshow-program")
    private Program program;
}
