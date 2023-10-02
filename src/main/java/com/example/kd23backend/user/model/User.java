package com.example.kd23backend.user.model;

import com.example.kd23backend.auth.model.Login;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dayOfRegister;

    @OneToOne
    @MapsId
    private Login login;
}
