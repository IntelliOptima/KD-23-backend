package com.example.kd23backend.theater.model.interfaces;

import com.example.kd23backend.theater.model.Theater;

public interface TheaterFactory {
    Theater createTheater();
    String[] getDisplayType();
    String getType();
}
