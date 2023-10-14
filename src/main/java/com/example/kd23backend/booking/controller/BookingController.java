package com.example.kd23backend.booking.controller;

import com.example.kd23backend.booking.model.Booking;
import com.example.kd23backend.booking.model.dtoObjects.BookingDTO;
import com.example.kd23backend.booking.service.BookingService;
import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.movie_show.service.MovieShowService;
import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.seat.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class    BookingController {

    private final BookingService bookingService;
    private final MovieShowService movieShowService;
    private final SeatService seatService;

    public BookingController(BookingService bookingService, MovieShowService movieShowService, SeatService service, SeatService seatService){
        this.bookingService = bookingService;
        this.movieShowService = movieShowService;
        this.seatService = seatService;
    }

    @PostMapping
    public ResponseEntity<List<Booking>> createBooking(@RequestBody List<BookingDTO> bookingRequests){
        Optional<MovieShow> movieShow = movieShowService.findMovieShowById(bookingRequests.get(0).getShowId());
        List<Booking> bookings = bookingRequests.stream().map(bookingRequest -> {
            Booking booking = new Booking();
            booking.setEmail(bookingRequest.getEmail());
            booking.setMovieShow(movieShow.orElse(null));
            Optional<Seat> seat = seatService.findBySeatId(bookingRequest.getSeatId());
            booking.setSeat(seat.orElse(null));
            return booking;
        }).toList();
        System.out.println("I'm Hit!!!");
        System.out.println(bookingRequests);
        List<Booking> createdBookings = bookingService.createBookings(bookings);
        return createdBookings.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(createdBookings);
    }

    @GetMapping("/find-all-by-movie-show/{id}")
    public ResponseEntity<Set<Booking>> findAllBookingsByMovieShowId(@PathVariable int id){
        System.out.println("I'm hit");
        List<Booking> bookings = bookingService.findAllBookingsByMovieShowId(id);
        Set<Booking> setBookings = new HashSet<>(bookings);
        System.out.println(bookings.size());
        return bookings.isEmpty() ? ResponseEntity.ok(Collections.emptySet()) : ResponseEntity.ok(setBookings);
    }

    @GetMapping("/find-all-by-user/{email}")
    public ResponseEntity<List<BookingDTO>> findAllBookingsByUserEmail(@PathVariable String email){
        List<BookingDTO> bookings = bookingService.findAllBookingsByUserEmail(email);
        return bookings.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(bookings);
    }

}
