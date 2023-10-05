package com.example.kd23backend.user.model;

import com.example.kd23backend.auth.model.Login;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dayOfRegister;

    @OneToOne
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private Login login;
}
