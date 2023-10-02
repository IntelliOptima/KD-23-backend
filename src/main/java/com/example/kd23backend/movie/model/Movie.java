package com.example.kd23backend.movie.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Year;
import java.util.Set;


@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private Year releaseYear;

    private String poster;

    private String trailer;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonManagedReference
    private Set<Actor> actors;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie-genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonManagedReference
    private Set<Genre> genres;

    private int minutes;



}
