package com.example.kd23backend.movie.model.dtoObjects;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class ActorDTO implements Std {
    private Integer id;
    private String name;
}
