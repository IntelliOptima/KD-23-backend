package com.example.kd23backend.movie.model;


import com.example.kd23backend.show.model.Show;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Year;
import java.util.List;
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

    private int playLengthInMinutes;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonManagedReference
    private Set<Actor> actors;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonManagedReference
    private Set<Genre> genres;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.MERGE)
    @JsonBackReference
    private Set<Show> shows;

}
