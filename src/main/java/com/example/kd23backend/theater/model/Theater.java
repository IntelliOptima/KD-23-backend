package com.example.kd23backend.theater.model;

import com.example.kd23backend.cinema.model.Cinema;
import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.movie_show.model.MovieShow;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = IMAXTheater.class, name = "IMAX"),
        @JsonSubTypes.Type(value = StandardTheater.class, name = "STANDARD") // Add other subtypes as needed
})
@Entity
public abstract class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer seatsPerRow;
    private Integer totalRows;


    @OneToMany(mappedBy = "theater")
    //@JsonManagedReference(value = "theater-seats")
    private List<Seat> seats;


    @OneToMany(mappedBy = "theater", cascade = CascadeType.MERGE)
    @JsonBackReference(value = "theater-movieshows")
    private Set<MovieShow> movieShows;


    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    @JsonBackReference(value = "theater-cinema")
    private Cinema cinema;


    /*
    @Transient
    protected TheaterImplementationStrategy implementationStrategy;

    public double getSeatPrice(Seat seat) {
        return implementationStrategy.calculateSeatPrice(seat);
    }
    */
}
