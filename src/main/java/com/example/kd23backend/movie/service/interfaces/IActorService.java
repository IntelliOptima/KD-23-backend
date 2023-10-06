package com.example.kd23backend.movie.service.interfaces;

import com.example.kd23backend.movie.model.Actor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IActorService {

    List<Actor> findActorByNameContainingIgnoreCase(String actorName, Pageable pageable);

    List<Actor> findAllActorsByMoviesContaining(Integer movieId, Pageable pageable);

}
