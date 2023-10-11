package com.example.kd23backend.theater.model;

import com.example.kd23backend.theater.model.util.imax_theater.IMAXImplementationStrategy;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("IMAX")
public class IMAXTheater extends Theater {

    @Override
    public Theater createTheater() {
        IMAXTheater imaxTheater = new IMAXTheater();
        imaxTheater.setImplementationStrategy(new IMAXImplementationStrategy());
        return imaxTheater;
    }
}
