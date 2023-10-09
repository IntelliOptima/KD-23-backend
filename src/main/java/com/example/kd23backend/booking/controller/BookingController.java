package com.example.kd23backend.booking.controller;

import com.example.kd23backend.booking.model.Booking;
import com.example.kd23backend.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking){
        return bookingService.createBooking(booking);
    }

    @GetMapping("/find-all-by-movie-show/{id}")
    public ResponseEntity<List<Booking>> findAllBookingsByMovieShowId(@PathVariable int id){
        List<Booking> bookings = bookingService.findAllBookingsByMovieShowId(id);
        return bookings.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(bookings);
    }

}
