package com.example.kd23backend.theater.model;

import com.example.kd23backend.theater.model.util.imax_theater.IMAXImplementationStrategy;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("IMAX")
public class IMAXTheater extends Theater {
    
    public IMAXTheater() {
        //this.implementationStrategy = new IMAXImplementationStrategy();
    }
}
