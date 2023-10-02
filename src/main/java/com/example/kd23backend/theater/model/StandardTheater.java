package com.example.kd23backend.theater.model;

import com.example.kd23backend.theater.model.util.standard_theater.StandardPricingStrategy;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STANDARD")
public class StandardTheater extends Theater {

    public StandardTheater() {
        this.pricingStrategy = new StandardPricingStrategy();
    }

}
