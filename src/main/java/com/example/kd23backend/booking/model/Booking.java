package com.example.kd23backend.booking.model;

import com.example.kd23backend.movie_show.model.MovieShow;
import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.theater.model.Theater;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Set;
=======
>>>>>>> 3bae46e2398ba0d055a061f26bb2d87d5273b2f4
@EqualsAndHashCode(exclude = "seat")
@Entity
@Data
@NoArgsConstructor
@JsonDeserialize(using = BookingDeserializer.class)
public class Booking{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Cascade(CascadeType.ALL)
    private Integer id;
    private String email;

    @ManyToOne
    @JoinColumn(name = "movie_show_id", referencedColumnName = "id")
    private MovieShow movieShow;

    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;
}
