package com.example.kd23backend.movie.repository;

import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    List<Actor> findByNameContainingIgnoreCase(String actorName);

    @Query(value = "SELECT a.* FROM Actor a " +
            "INNER JOIN movie_actor ma ON a.id = ma.actor_id " +
            "WHERE ma.movie_id = :movieId",
            nativeQuery = true)
    List<Actor> findAllByMoviesContaining(@Param("movieId") Integer movieId);
}

