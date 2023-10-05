package com.example.kd23backend.theater.model;

import com.example.kd23backend.cinema.model.Cinema;
import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.theater.model.interfaces.TheaterImplementationStrategy;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Entity
public abstract class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer seatsPerRow;
    private Integer totalRows;


    @OneToMany(mappedBy = "theater")
    @JsonManagedReference
    private TreeSet<Seat> seats;


    @OneToMany(mappedBy = "theater", cascade = CascadeType.MERGE)
    @JsonBackReference
    private Set<MovieShow> movieShows;


    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    @JsonBackReference
    private Cinema cinema;

    public Theater() {
        this.seats = new TreeSet<>(Comparator.comparingInt(Seat::getSeatNum));
    }

    @Transient
    protected TheaterImplementationStrategy implementationStrategy;

    public double getSeatPrice(Seat seat) {
        return implementationStrategy.calculateSeatPrice(seat);
    }

}
