package com.example.kd23backend.theater.controller;

import com.example.kd23backend.theater.model.Theater;
import com.example.kd23backend.theater.service.TheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
@CrossOrigin
public class TheaterController {
    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/cinema/{id}")
    public ResponseEntity<List<Theater>> getTheatersByCinemaId(@PathVariable int id){
        List<Theater> theaters = theaterService.getTheatersForCinemaId(id);
        return theaters.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : ResponseEntity.ok(theaters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getSpecificTheater(@PathVariable int id) {
        return ResponseEntity.ok(theaterService.getSpecificTheater(id));
    }

}
