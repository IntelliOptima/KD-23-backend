package com.example.kd23backend.seat.controller;

import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.seat.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService){
        this.seatService = seatService;
    }


    @GetMapping("/find-all-by-theater/{id}")
    public ResponseEntity<List<Seat>> findAllSeatsByTheaterId(@PathVariable int id){
        List<Seat> seats = seatService.findAllByTheaterId(id);
        return seats.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(seats);
    }
}
