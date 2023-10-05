package com.example.kd23backend.movie.service;

import com.example.kd23backend.movie.model.Actor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IActorService {

    List<Actor> findActorByNameContainingIgnoreCase(String actorName);

    List<Actor> findAllActorsByMoviesContaining(@Param("movieId") Integer movieId);
}
