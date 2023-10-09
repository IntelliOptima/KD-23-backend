package com.example.kd23backend.booking.repository;

import com.example.kd23backend.booking.model.Booking;
import com.example.kd23backend.booking.model.dtoObjects.BookingDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllBookingsByMovieShowId(int movieShowId);
}
