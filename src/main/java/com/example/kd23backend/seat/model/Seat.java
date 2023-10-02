package com.example.kd23backend.seat.model;

import com.example.kd23backend.theater.model.Theater;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer price;
    private Integer seatNum;


    @ManyToOne
    @JoinColumn(name = "theater_id", referencedColumnName = "id")
    @JsonBackReference
    private Theater theater;
}
