package com.example.kd23backend.booking.controller;

import com.example.kd23backend.booking.model.Booking;
import com.example.kd23backend.booking.model.dtoObjects.BookingDTO;
import com.example.kd23backend.booking.service.BookingService;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<List<Booking>> createBooking(@RequestBody List<Booking> bookings){
        System.out.println(bookings);
        List<Booking> createdBookings = bookingService.createBookings(bookings);
        return createdBookings.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(createdBookings);
    }

    @GetMapping("/find-all-by-movie-show/{id}")
    public ResponseEntity<List<BookingDTO>> findAllBookingsByMovieShowId(@PathVariable int id){
        List<BookingDTO> bookings = bookingService.findAllBookingsByMovieShowId(id);
        return bookings.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(bookings);
    }

    @GetMapping("/find-all-by-user/{email}")
    public ResponseEntity<List<BookingDTO>> findAllBookingsByUserEmail(@PathVariable String email){
        List<BookingDTO> bookings = bookingService.findAllBookingsByUserEmail(email);
        return bookings.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(bookings);
    }

}
