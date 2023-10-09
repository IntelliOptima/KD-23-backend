package com.example.kd23backend.booking.service;

import com.example.kd23backend.booking.model.Booking;
import com.example.kd23backend.booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService{

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAllBookingsByMovieShowId(int movieShowId) {
        return bookingRepository.findAllBookingsByMovieShowId(movieShowId);
    }
}
