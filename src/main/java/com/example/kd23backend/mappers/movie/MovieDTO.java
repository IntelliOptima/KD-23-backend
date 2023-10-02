package com.example.kd23backend.mappers.movie;

import com.example.kd23backend.movie.model.Genre;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * {
 *       "adult": false,
 *       "backdrop_path": "/1syW9SNna38rSl9fnXwc9fP7POW.jpg",
 *       "genre_ids": [
 *         28,
 *         878,
 *         12
 *       ],
 *       "id": 565770,
 *       "original_language": "en",
 *       "original_title": "Blue Beetle",
 *       "overview": "Recent college grad Jaime Reyes returns home full of aspirations for his future, only to find that home is not quite as he left it. As he searches to find his purpose in the world, fate intervenes when Jaime unexpectedly finds himself in possession of an ancient relic of alien biotechnology: the Scarab.",
 *       "popularity": 3386.97,
 *       "poster_path": "/mXLOHHc1Zeuwsl4xYKjKh2280oL.jpg",
 *       "release_date": "2023-08-16",
 *       "title": "Blue Beetle",
 *       "video": false,
 *       "vote_average": 7.1,
 *       "vote_count": 1059
 *     },
 */
@Data
public class MovieDTO implements Serializable {

    private int id;
    private boolean adult;
    private Genre[] genre_ids;
    private String overview;
    private String poster_path;
    private LocalDate release_date;
    private String title;
    private float vote_average;


}
