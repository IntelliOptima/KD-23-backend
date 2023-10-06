package com.example.kd23backend.seat.repository;

import com.example.kd23backend.seat.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
