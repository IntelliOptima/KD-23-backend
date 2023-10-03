package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, String> {

}
