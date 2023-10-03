package com.example.kd23backend.movie.mappers;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResultDTO {
    private List<TrailerDTO> trailers;
}
