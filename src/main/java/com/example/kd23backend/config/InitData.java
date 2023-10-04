package com.example.kd23backend.config;

import com.example.kd23backend.auth.config.ApplicationConfig;
import com.example.kd23backend.auth.model.Login;
import com.example.kd23backend.auth.model.Role;
import com.example.kd23backend.auth.repository.UserRepo;
import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.service.IMovieAPIService;
import com.example.kd23backend.movie.service.MovieAPIService;
import com.example.kd23backend.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    String apiTest = "https://api.themoviedb.org/3/movie/18?api_key=bf45e26b2cbe79b9bcb399d646313e59&append_to_response=videos,credits";

    @Autowired
    ApplicationConfig applicationConfig;

    @Autowired
    UserRepo userRepo;

    @Autowired
    MovieAPIService movieAPIService;

    @Autowired
    MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        //movieAPIService.fetchAllMovies();
        //System.out.println(movieService.findAllByPosterIsNot("movie has no trailer"));
        Movie fetchedMovie = movieService.findByTitle("star wars");
        System.out.println(fetchedMovie != null);
        //System.out.println(movieService.getSpecificMovie(7279));
       // Login login = new Login();
        //login.setEmail("test@test.dk");
        //login.setPassword(applicationConfig.passwordEncoder().encode("Test1234"));
        //login.setRole(Role.USER);
        //userRepo.save(login);
    }
}
