package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ActorRepository extends JpaRepository<Actor, String> {

    @Query("SELECT a.name FROM Actor a")
    Set<String> findAllNames();

}
