package com.example.kd23backend.theater.model.interfaces;

import com.example.kd23backend.seat.model.Seat;

public interface TheaterImplementationStrategy {
    double calculateSeatPrice(Seat seat);
}
