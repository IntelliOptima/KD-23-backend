package com.example.kd23backend.theater.model.util.standard_theater;

import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.theater.model.interfaces.PricingStrategy;

public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calculateSeatPrice(Seat seat) {
        // logic for standard pricing, for example:
        return 10.0; // flat rate for standard seats
    }
}
