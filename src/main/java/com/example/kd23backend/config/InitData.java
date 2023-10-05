package com.example.kd23backend.config;

import com.example.kd23backend.auth.config.ApplicationConfig;
import com.example.kd23backend.auth.model.Login;
import com.example.kd23backend.auth.model.Role;
import com.example.kd23backend.auth.repository.UserRepo;
import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.service.IMovieAPIService;
import com.example.kd23backend.movie.service.MovieAPIService;
import com.example.kd23backend.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional
    public void run(String... args) throws Exception {
        //movieAPIService.fetchAllMovies();
       // List<Movie> movieList = movieService.findAllByPosterIsNot("movie has no poster");
     //   System.out.println("findAllBYPOSTERISNOT LIST SIZE (0) = " + movieList.size());
       Optional<Movie> fetchedMovie = movieService.findByTitle("STAR wars");
        fetchedMovie.ifPresent(movie -> movie.getActors().stream().map(Actor::getMovies).toList().forEach(System.out::println));
        //Movie movie = movieService.getSpecificMovie(7279);
        //System.out.println(movie.getTitle());
        //movieList = movieService.findAllByTrailerIsNot("movie has no trailer");
        //System.out.println("findAllByTrailerIsNot LIST SIZE (greaterthan0) = " + movieList.size());
        //movieList = movieService.findAllByIsAdultTrue();
        //System.out.println("findAllByAdultTrue LIST SIZE = " + movieList.size());
        //movieList = movieService.findAllByIsAdultFalse();
        //System.out.println("findAllByAdultFalse LIST SIZE = " + movieList.size());
        //movieList = movieService.findAllByRuntimeLessThan(60);
        //System.out.println("findAllByRuntimeLassthan 60 LIST SIZE = " + movieList.size());

        //Login login = new Login();
        //login.setEmail("test@test.dk");
        //login.setPassword(applicationConfig.passwordEncoder().encode("Test1234"));
        //login.setRole(Role.USER);
        //userRepo.save(login);
    }
}
