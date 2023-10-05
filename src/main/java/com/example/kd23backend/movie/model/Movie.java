package com.example.kd23backend.movie.model;


import com.example.kd23backend.movie.model.dtoObjects.MovieDTO;
import com.example.kd23backend.movie_show.model.MovieShow;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonDeserialize(using = MovieDTO.class)
public class Movie {
    @Id
    private Integer id;

    private String title;

    private boolean isAdult;

    private LocalDate releaseDate;

    private String poster;

    private String trailer;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int runtime;

    private double voteRating;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonManagedReference(value = "genres")
    @JsonIgnore
    private Set<Genre> genres;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.MERGE)
    @JsonBackReference(value = "movie-show")
    @JsonIgnore
    private Set<MovieShow> movieShows;

}
