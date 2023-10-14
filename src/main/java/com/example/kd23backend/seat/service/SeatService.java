package com.example.kd23backend.seat.service;

import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.seat.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService implements ISeatService{

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository){
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> findAllByTheaterId(int id) {
        return seatRepository.findAllByTheaterId(id);
    }

    @Override
    public Optional<Seat> findBySeatId(int id) {
        return seatRepository.findById(id);
    }
}
