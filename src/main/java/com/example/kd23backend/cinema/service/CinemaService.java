package com.example.kd23backend.cinema.service;

import com.example.kd23backend.cinema.model.Cinema;
import com.example.kd23backend.cinema.repository.CinemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class CinemaService implements ICinemaService{
    private final CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository){
        this.cinemaRepository = cinemaRepository;
    }
    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema createCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }


}
