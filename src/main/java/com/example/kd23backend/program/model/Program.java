package com.example.kd23backend.program.model;

import com.example.kd23backend.cinema.model.Cinema;
import com.example.kd23backend.movie_show.model.MovieShow;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    @JsonBackReference(value = "program-cinema")
    private Cinema cinema;

    @OneToMany(mappedBy = "program")
    //@JsonManagedReference(value = "program-movieshows")
    private Set<MovieShow> movieShows;
}
