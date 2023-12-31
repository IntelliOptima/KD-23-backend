package com.example.kd23backend.theater.model.util.imax_theater;

import com.example.kd23backend.theater.model.IMAXTheater;
import com.example.kd23backend.theater.model.Theater;
import com.example.kd23backend.theater.model.interfaces.TheaterFactory;

public class IMAXTheaterFactory implements TheaterFactory {
    @Override
    public Theater createTheater() {
        return new IMAXTheater();
    }

    @Override
    public String[] getDisplayType() {
        return new String[]{"2D", "3D", "4D"};
    }

    @Override
    public String getType() {
        return "IMAX";
    }
}
