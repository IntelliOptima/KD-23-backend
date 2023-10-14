package com.example.kd23backend.booking.service;

import com.example.kd23backend.booking.model.Booking;
import com.example.kd23backend.booking.model.dtoObjects.BookingDTO;

import java.util.List;

public interface IBookingService {

    List<Booking> createBookings(List<Booking> booking);

    List<Booking> findAllBookingsByMovieShowId(int movieShowId);

    List<BookingDTO> findAllBookingsByUserEmail(String email);
}
