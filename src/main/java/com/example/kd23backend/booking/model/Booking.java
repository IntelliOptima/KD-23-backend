package com.example.kd23backend.booking.model;

import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.theater.model.Theater;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Booking{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;


    @ManyToOne
    @JoinColumn(name = "movie_show_id", referencedColumnName = "id")
    private MovieShow movieShow;


    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;
}
