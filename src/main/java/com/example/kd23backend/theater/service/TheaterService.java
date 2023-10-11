package com.example.kd23backend.theater.service;

import com.example.kd23backend.theater.model.Theater;
import com.example.kd23backend.theater.model.interfaces.TheaterFactory;
import com.example.kd23backend.theater.model.util.imax_theater.IMAXTheaterFactory;
import com.example.kd23backend.theater.model.util.standard_theater.StandardTheaterFactory;
import com.example.kd23backend.theater.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Theater> getTheatersForCinemaId(int id) {
        return theaterRepository.findAllByCinema_Id(id);
    }

    @Override
    public List<Theater> getTheaters() {
        return null;
    }
}
