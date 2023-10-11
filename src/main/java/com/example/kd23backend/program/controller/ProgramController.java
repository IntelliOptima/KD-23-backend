package com.example.kd23backend.program.controller;

import com.example.kd23backend.cinema.service.ICinemaService;
import com.example.kd23backend.program.model.Program;
import com.example.kd23backend.program.model.ProgramDeserializer;
import com.example.kd23backend.program.repository.ProgramRepository;
import com.example.kd23backend.program.service.IProgramService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/program")
public class ProgramController {
    private IProgramService programService;

    public ProgramController(IProgramService iProgramService) {
        programService = iProgramService;
    }

    @GetMapping("{cinemaId}")
    public ResponseEntity<List<Program>> getAllProgramsForCinema(@PathVariable Integer cinemaId) {
        List<Program> programs = programService.getAllProgramsForCinema(cinemaId);
        return programs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(programs);
    }

    @PostMapping
    public ResponseEntity<Program> findMovieShowById(
            @RequestBody Program program){
        System.out.println("Im hit");
        Program createdProgram = programService.createProgram(program);
        return ResponseEntity.ok(program);
    }
}
