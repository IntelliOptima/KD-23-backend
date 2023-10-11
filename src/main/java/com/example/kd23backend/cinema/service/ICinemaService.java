package com.example.kd23backend.cinema.service;

import com.example.kd23backend.cinema.model.Cinema;

import java.util.List;
import java.util.Optional;

public interface ICinemaService {

    List<Cinema> getAllCinemas();

    Cinema createCinema(Cinema cinema);
    Optional<Cinema> getCinemaByID(Integer cinemaID);
}
