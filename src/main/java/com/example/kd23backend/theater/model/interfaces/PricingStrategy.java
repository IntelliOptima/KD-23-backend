package com.example.kd23backend.theater.model.interfaces;

import com.example.kd23backend.seat.model.Seat;

public interface PricingStrategy {
    double calculateSeatPrice(Seat seat);
}
