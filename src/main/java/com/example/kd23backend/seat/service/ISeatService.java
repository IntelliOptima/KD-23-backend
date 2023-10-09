package com.example.kd23backend.seat.service;

import com.example.kd23backend.seat.model.Seat;

import java.util.List;

public interface ISeatService {

    List<Seat> findAllByTheaterId(int id);
}
