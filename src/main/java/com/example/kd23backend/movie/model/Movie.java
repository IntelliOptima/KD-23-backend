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

    /**
     * {
     *       "adult": false,
     *       "backdrop_path": "/1syW9SNna38rSl9fnXwc9fP7POW.jpg",
     *       "genre_ids": [
     *         28,
     *         878,
     *         12
     *       ],
     *       "id": 565770,
     *       "original_language": "en",
     *       "original_title": "Blue Beetle",
     *       "overview": "Recent college grad Jaime Reyes returns home full of aspirations for his future, only to find that home is not quite as he left it. As he searches to find his purpose in the world, fate intervenes when Jaime unexpectedly finds himself in possession of an ancient relic of alien biotechnology: the Scarab.",
     *       "popularity": 3386.97,
     *       "poster_path": "/mXLOHHc1Zeuwsl4xYKjKh2280oL.jpg",
     *       "release_date": "2023-08-16",
     *       "title": "Blue Beetle",
     *       "video": false,
     *       "vote_average": 7.1,
     *       "vote_count": 1059
     *     },
     */

    @Entity
    @NamedEntityGraph(
            name = "movieOnly",
            attributeNodes = {
                    @NamedAttributeNode("id"),
                    @NamedAttributeNode("title"),
                    @NamedAttributeNode("isAdult"),
                    @NamedAttributeNode("releaseDate"),
                    @NamedAttributeNode("poster"),
                    @NamedAttributeNode("trailer"),
                    @NamedAttributeNode("description"),
                    @NamedAttributeNode("runtime"),
                    @NamedAttributeNode("voteRating"),
            }
    )
    @Data
    @NoArgsConstructor
    @JsonDeserialize(using = MovieDTO.class)
    public class Movie {
        @Id
        private Integer id;

        @JsonProperty
        private String title;

        private boolean isAdult;

        private LocalDate releaseDate;

        private String poster;

        private String trailer;

        @Column(columnDefinition = "TEXT")
        private String description;

        private int runtime;

        private double voteRating;

        @ManyToMany
        @JoinTable(
                name = "movie_actor",
                joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name = "actor_id")
        )
        @JsonManagedReference
        private Set<Actor> actors;

        @ManyToMany
        @JoinTable(
                name = "movie_genre",
                joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name = "genre_id")
        )
        @JsonManagedReference
        private Set<Genre> genres;

        @OneToMany(mappedBy = "movie", cascade = CascadeType.MERGE)
        @JsonBackReference(value = "movie-show")
        private Set<MovieShow> movieShows;

    }
