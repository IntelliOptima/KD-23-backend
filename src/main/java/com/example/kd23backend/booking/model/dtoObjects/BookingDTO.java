package com.example.kd23backend.booking.model.dtoObjects;

import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.seat.model.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private Integer id;
    private String email;
    private MovieShow movieShow;
    private Seat seat;

    public BookingDTO(Integer id, String email, MovieShow movieShow, Seat seat) {
        this.id = id;
        this.email = email;
        this.movieShow = movieShow;
        this.seat = seat;
    }
}
