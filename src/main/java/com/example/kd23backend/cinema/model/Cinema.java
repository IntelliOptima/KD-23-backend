package com.example.kd23backend.cinema.model;

import com.example.kd23backend.address.model.Address;
import com.example.kd23backend.employee.model.Employee;
import com.example.kd23backend.theater.model.Theater;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne
    @MapsId
    private Address address;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Employee> employees;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Theater> theaters;
}
