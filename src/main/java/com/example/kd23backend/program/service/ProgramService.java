package com.example.kd23backend.program.service;

import com.example.kd23backend.program.model.Program;
import com.example.kd23backend.program.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService implements IProgramService {
    private ProgramRepository programRepository;
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<Program> getAllProgramsForCinema(Integer cinemaId) {
        return programRepository.findAllByCinema_Id(cinemaId);
    }

    @Override
    public void createProgram(Program program) {
        programRepository.save(program);
    }
}
