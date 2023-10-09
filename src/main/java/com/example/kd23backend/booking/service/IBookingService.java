package com.example.kd23backend.booking.service;

import com.example.kd23backend.booking.model.Booking;
import com.example.kd23backend.booking.model.dtoObjects.BookingDTO;

import java.util.List;

public interface IBookingService {

    Booking createBooking(Booking booking);

    List<BookingDTO> findAllBookingsByMovieShowId(int movieShowId);
}
