package com.example.kd23backend.booking.repository;

import com.example.kd23backend.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
