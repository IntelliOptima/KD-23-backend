package com.example.kd23backend.movie.mappers;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrailerDTO {
    private Integer id;
    private String type;
    private String site;
    private String key;
}
