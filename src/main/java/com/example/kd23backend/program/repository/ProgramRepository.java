package com.example.kd23backend.program.repository;

import com.example.kd23backend.program.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
    Program findFirstByOrderByIdDesc();
    List<Program> findAllByCinema_Id(Integer cinemaId);
}
