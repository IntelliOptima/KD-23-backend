package com.example.kd23backend.booking.service;

import com.example.kd23backend.booking.model.Booking;

import java.util.List;

public interface IBookingService {

    Booking createBooking(Booking booking);

    List<Booking> findAllBookingsByMovieShowId(int movieShowId);
}
