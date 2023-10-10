package com.example.kd23backend.theater.repository;

import com.example.kd23backend.theater.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    List<Theater> findAllByCinema_Id(Integer id);

}
