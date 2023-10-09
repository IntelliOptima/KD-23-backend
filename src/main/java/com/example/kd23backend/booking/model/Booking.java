package com.example.kd23backend.booking.model;

import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.theater.model.Theater;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;


    @ManyToOne
    @JoinColumn(name = "theater_id", referencedColumnName = "id")
    private Theater theater;


    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;
}
