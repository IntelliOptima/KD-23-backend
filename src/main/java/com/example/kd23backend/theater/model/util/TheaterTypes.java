package com.example.kd23backend.theater.model.util;

import com.example.kd23backend.theater.model.interfaces.TheaterFactory;
import com.example.kd23backend.theater.model.util.imax_theater.IMAXTheaterFactory;
import com.example.kd23backend.theater.model.util.standard_theater.StandardTheaterFactory;
import lombok.Getter;

@Getter

public enum TheaterTypes {
    STANDARD(new StandardTheaterFactory()),
    IMAX(new IMAXTheaterFactory());

    private final TheaterFactory factory;

    TheaterTypes(TheaterFactory factory) {
        this.factory = factory;
    }

}
