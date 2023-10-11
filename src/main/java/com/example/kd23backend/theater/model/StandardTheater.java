package com.example.kd23backend.theater.model;

import com.example.kd23backend.theater.model.util.standard_theater.StandardImplementationStrategy;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STANDARD")
public class StandardTheater extends Theater {

    @Override
    public Theater createTheater() {
        StandardTheater standardTheater = new StandardTheater();
        standardTheater.setImplementationStrategy(new StandardImplementationStrategy());
        return standardTheater;
    }
}
