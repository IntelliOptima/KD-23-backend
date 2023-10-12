package com.example.kd23backend.program.service;

import com.example.kd23backend.program.model.Program;

import java.util.List;
import java.util.Optional;

public interface IProgramService {
    List<Program> getAllProgramsForCinema(Integer cinemaId);
    Program createProgram(Program program);

    Program findFirstByOrderByIdDesc();
}
