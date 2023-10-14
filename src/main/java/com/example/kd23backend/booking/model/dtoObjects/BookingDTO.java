package com.example.kd23backend.booking.model.dtoObjects;

import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.seat.model.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private Integer id;
    private String email;
    private Integer showId;
    private Integer seatId;

}
