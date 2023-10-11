package com.example.kd23backend.theater.model.util;

import com.example.kd23backend.theater.model.Theater;

public class TheaterManager {
    public static Theater createTheater(String type) {
        return TheaterTypes.valueOf(type).getFactory().createTheater();
    }
}
