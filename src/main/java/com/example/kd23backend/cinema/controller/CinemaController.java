package com.example.kd23backend.cinema.controller;

import com.example.kd23backend.cinema.model.Cinema;
import com.example.kd23backend.cinema.service.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@CrossOrigin(origins = "http://localhost:3000")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService){
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public ResponseEntity<List<Cinema>> getCinemas(){
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        return cinemas.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(cinemas);
    }

    @PostMapping
    public Cinema createAndUpdateCinema(@RequestBody Cinema cinema){
        System.out.println("Log when you reach this postmapping");
        return cinemaService.createCinema(cinema);
    }
}
