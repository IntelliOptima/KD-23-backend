package com.example.kd23backend.theater.service;

import com.example.kd23backend.theater.model.Theater;
import com.example.kd23backend.theater.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService implements ITheaterService {

    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @Override
    public Theater getSpecificTheater(int id) {
        return theaterRepository.findById(id).orElseThrow();
    }


    @Override
    public List<Theater> getTheaters() {
        return null;
    }
}
