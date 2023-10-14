package com.example.kd23backend.booking.service;

import com.example.kd23backend.booking.model.Booking;
import com.example.kd23backend.booking.model.dtoObjects.BookingDTO;
import com.example.kd23backend.booking.repository.BookingRepository;
import com.example.kd23backend.movie_show.model.MovieShow;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService implements IBookingService{

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> createBookings(List<Booking> bookings) {
        return bookingRepository.saveAll(bookings);
    }

    @Override
    public List<Booking> findAllBookingsByMovieShowId(int movieShowId) {
        List<Booking> bookings = bookingRepository.findAllBookingsByMovieShowId(movieShowId);
        return bookings;
    }

    @Override
    public List<BookingDTO> findAllBookingsByUserEmail(String email) {
        List<Booking> bookings = bookingRepository.findBookingsByEmail(email);
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDTO dto = mapBookingToDTO(booking);
            bookingDTOS.add(dto);
        }
        return bookingDTOS;
    }


    public BookingDTO mapBookingToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setEmail(booking.getEmail());

        MovieShow movieShow = new MovieShow();
        movieShow.setId(booking.getMovieShow().getId());
        movieShow.setStartDateTime(booking.getMovieShow().getStartDateTime());
        movieShow.setPrice(booking.getMovieShow().getPrice());
        movieShow.setMovie(booking.getMovieShow().getMovie());

//        dto.setShowId(movieShow);

//        dto.setSeat(booking.getSeat());

        return dto;
    }


}
