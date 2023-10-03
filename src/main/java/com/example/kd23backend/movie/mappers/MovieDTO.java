package com.example.kd23backend.movie.mappers;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieDTO implements Serializable {

    private int id;
    private boolean adult;
    private List<Genre> genres;
    private List<Actor> actors;
    private String overview;
    private String poster_path;
    private VideoDTO videos;
    private LocalDate release_date;
    private String title;
    private float vote_average;

}
