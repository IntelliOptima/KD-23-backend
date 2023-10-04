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
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")

    @Entity
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

        @ManyToMany()
        @JoinTable(
                name = "movie_actor",
                joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name = "actor_id")
        )
        @JsonManagedReference
        private Set<Actor> actors;

        @ManyToMany()
        @JoinTable(
                name = "movie_genre",
                joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name = "genre_id")
        )
        @JsonManagedReference
        private Set<Genre> genres;

        @OneToMany(mappedBy = "movie", cascade = CascadeType.MERGE)
        @JsonBackReference
        private Set<MovieShow> movieShows;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Movie other = (Movie) obj;
            if (id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!id.equals(other.id)) {
                return false;
            }
            return true;
        }
    }
