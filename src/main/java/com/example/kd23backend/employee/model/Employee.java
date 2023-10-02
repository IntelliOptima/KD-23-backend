package com.example.kd23backend.employee.model;

import com.example.kd23backend.auth.model.Login;
import com.example.kd23backend.cinema.model.Cinema;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int wagePerHour;

    private LocalDate dateOfHiring;

    @ManyToOne()
    @JoinColumn(name = "cinema_id", referencedColumnName = "cinema")
    @JsonBackReference
    private Cinema cinema;

    @OneToOne
    @MapsId
    private Login login;
}
