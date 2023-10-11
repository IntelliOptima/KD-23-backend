package com.example.kd23backend.program.service;

import com.example.kd23backend.movie_show.repository.MovieShowRepository;
import com.example.kd23backend.program.model.Program;
import com.example.kd23backend.program.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService implements IProgramService {
    private final ProgramRepository programRepository;

    private final MovieShowRepository movieShowRepository;
    public ProgramService(ProgramRepository programRepository, MovieShowRepository movieShowRepository) {
        this.programRepository = programRepository;
        this.movieShowRepository = movieShowRepository;
    }

    @Override
    public List<Program> getAllProgramsForCinema(Integer cinemaId) {
        return programRepository.findAllByCinema_Id(cinemaId);
    }

    @Override
    public Program createProgram(Program program) {
        return programRepository.save(program);
    }

    @Override
    public Program findFirstByOrderByIdDesc() {
        return programRepository.findFirstByOrderByIdDesc();
    }

    public Program createProgram2(Program program) {
        // Save the program first to generate its ID
        Program savedProgram = programRepository.save(program);

        // Update the program for each MovieShow and save them
        program.getMovieShows().forEach(movieShow -> {
            movieShow.setProgram(savedProgram);
            movieShowRepository.save(movieShow);
        });

        return savedProgram;
    }
}
