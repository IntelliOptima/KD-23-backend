package com.example.kd23backend.program.model;

import com.example.kd23backend.cinema.model.Cinema;
import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.program.model.serializer.ProgramDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(exclude = "movieShows")
@Entity
@Data
@NoArgsConstructor
@JsonDeserialize(using = ProgramDeserializer.class)
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

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<MovieShow> movieShows;
}
