package com.example.kd23backend.theater.model.util.imax_theater;

import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.theater.model.interfaces.PricingStrategy;

public class IMAXPricingStrategy implements PricingStrategy {
    @Override
    public double calculateSeatPrice(Seat seat) {
        // Logic for calculating seatPrice and return the value
        return 15.0;
    }
}
