package com.example.kd23backend.theater.model;

import com.example.kd23backend.seat.model.Seat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Comparator;
import java.util.TreeSet;

@Entity
@Data
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int seatsPerRow;
    private int row;
    

    @OneToMany(mappedBy = "theater")
    @JsonManagedReference
    private TreeSet<Seat> seats;

    public Theater() {
        this.seats = new TreeSet<>(Comparator.comparingInt(Seat::getSeatNum));
    }
}
