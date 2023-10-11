package com.example.kd23backend.theater.model.util.standard_theater;

import com.example.kd23backend.theater.model.StandardTheater;
import com.example.kd23backend.theater.model.Theater;
import com.example.kd23backend.theater.model.interfaces.TheaterFactory;

public class StandardTheaterFactory implements TheaterFactory {
    @Override
    public Theater createTheater() {
        return new StandardTheater();
    }
}
