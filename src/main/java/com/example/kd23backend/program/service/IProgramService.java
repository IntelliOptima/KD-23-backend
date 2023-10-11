package com.example.kd23backend.program.service;

import com.example.kd23backend.program.model.Program;

import java.util.List;

public interface IProgramService {
    List<Program> getAllProgramsForCinema(Integer cinemaId);
    void createProgram(Program program);
}
