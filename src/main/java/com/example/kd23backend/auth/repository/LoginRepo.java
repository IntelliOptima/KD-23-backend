package com.example.kd23backend.auth.repository;


import com.example.kd23backend.auth.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<Login, Integer> {
    Optional<Login> findByEmail(String email);
}
