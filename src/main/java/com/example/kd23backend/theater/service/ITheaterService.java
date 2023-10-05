package com.example.kd23backend.theater.service;

import com.example.kd23backend.theater.model.Theater;

import java.util.List;

public interface ITheaterService {
    Theater getSpecificTheater(int id);

    List<Theater> getTheaters();
}
