package com.example.kd23backend.seat.service;

import com.example.kd23backend.seat.model.Seat;

import java.util.List;
import java.util.Optional;

public interface ISeatService {

    List<Seat> findAllByTheaterId(int id);
    Optional<Seat> findBySeatId(int id);
}
