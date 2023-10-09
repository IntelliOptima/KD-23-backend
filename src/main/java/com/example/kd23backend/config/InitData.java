package com.example.kd23backend.config;

import com.example.kd23backend.address.model.Address;
import com.example.kd23backend.address.repository.AddressRepository;
import com.example.kd23backend.auth.config.ApplicationConfig;
import com.example.kd23backend.auth.model.Login;
import com.example.kd23backend.auth.model.Role;
import com.example.kd23backend.auth.repository.UserRepo;
import com.example.kd23backend.booking.model.Booking;
import com.example.kd23backend.booking.repository.BookingRepository;
import com.example.kd23backend.cinema.model.Cinema;
import com.example.kd23backend.cinema.repository.CinemaRepository;
import com.example.kd23backend.movie.model.Actor;
import com.example.kd23backend.movie.model.Genre;
import com.example.kd23backend.movie.model.Movie;
import com.example.kd23backend.movie.service.MovieAPIService;
import com.example.kd23backend.movie.service.MovieService;
import com.example.kd23backend.seat.model.Seat;
import com.example.kd23backend.seat.repository.SeatRepository;
import com.example.kd23backend.theater.model.StandardTheater;
import com.example.kd23backend.theater.model.Theater;
import com.example.kd23backend.theater.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
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

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        //Init data for cinema -----------------------------------------------
        /*
        Address address2 = new Address();
        address2.setStreet("Blahblah");
        address2.setNumber("69");
        address2.setCity("Slagelse");
        address2.setZip("420");
        address2.setCountry("Danmark");
        addressRepository.save(address2);


       Address address = new Address();

        address.setStreet("Biografstræde");
        address.setNumber("69");
        address.setCity("Slagelse");
        address.setZip("4200");
        address.setCountry("Danmark");
        addressRepository.save(address);

        Cinema cinema = new Cinema();
        cinema.setName("KinoXp");
        cinema.setAddress(address);
        cinemaRepository.save(cinema);

        Theater standardTheater = new StandardTheater();
        standardTheater.setTotalRows(10);
        standardTheater.setSeatsPerRow(5);
        standardTheater.setCinema(cinema);
        theaterRepository.save(standardTheater);


        Seat seat = new Seat();
        for (int i = 0; i < standardTheater.getSeatsPerRow() * standardTheater.getTotalRows(); i++){
            seat.setPriceWeight(1);
            seat.setTheater(standardTheater);
            seatRepository.save(seat);
            seat = new Seat();
        }
        Seat newSeat = seatRepository.findAll().get(0);
        Booking booking = new Booking();
        booking.setTheater(standardTheater);
        booking.setEmail("MKsnmdkasd");
        booking.setSeat(newSeat);
        bookingRepository.save(booking);



        //movieAPIService.fetchAllMovies();
        //List<Movie> movieList = movieService.findAllByPosterIsNot("movie has no poster");
        //System.out.println("findAllBYPOSTERISNOT LIST SIZE (0) = " + movieList.size());
        //List<Movie> movies = new ArrayList<>();
        //movies.add(movieService.findByTitle("Ariel").get());
        //Actor actor = movies.get(0).getActors().stream().limit(1).toList().get(0);
        //Actor tomHanks = new Actor();
        //tomHanks.setId(31);
        //tomHanks.setName("Tom Hanks");
        //Genre genre = movies.get(0).getGenres().stream().limit(1).toList().get(0);
        //List<Movie> moviesByGenre = movieService.findAllByGenresContaining(genre);
        //moviesByGenre.forEach(System.out::println);
        //List<Movie> moviesByActor = movieService.findAllByActorsContaining(tomHanks);
        //moviesByActor.forEach(System.out::println);
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
        */

    }
}
