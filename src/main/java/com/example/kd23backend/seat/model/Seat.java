package com.example.kd23backend.seat.model;

import com.example.kd23backend.booking.model.Booking;
import com.example.kd23backend.theater.model.Theater;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

=======
>>>>>>> 3bae46e2398ba0d055a061f26bb2d87d5273b2f4
@EqualsAndHashCode(exclude = "booking")
@Entity
@Data
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private double priceWeight;

    private int row;

    private int numberInRow;

    @ManyToOne
    @JoinColumn(name = "theater_id", referencedColumnName = "id")
    @JsonBackReference(value = "seat-theater")
    private Theater theater;

    @OneToOne(mappedBy = "seat")
    @JsonBackReference(value = "seat-booking")
    private Booking booking;
}
