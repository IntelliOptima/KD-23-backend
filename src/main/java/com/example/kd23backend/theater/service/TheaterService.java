package com.example.kd23backend.theater.service;

import com.example.kd23backend.theater.model.Theater;
import com.example.kd23backend.theater.model.interfaces.TheaterFactory;
import com.example.kd23backend.theater.model.util.imax_theater.IMAXTheaterFactory;
import com.example.kd23backend.theater.model.util.standard_theater.StandardTheaterFactory;
import com.example.kd23backend.theater.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TheaterService implements ITheaterService {
    private final Map<String, TheaterFactory> factoryMap = new HashMap<>();

    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        registerFactory(new StandardTheaterFactory());
        registerFactory(new IMAXTheaterFactory());
        this.theaterRepository = theaterRepository;
    }

    private void registerFactory(TheaterFactory factory) {
        factoryMap.put(factory.getType(), factory);
    }

    public Theater createTheater(String theaterType) {
        TheaterFactory factory = factoryMap.get(theaterType);
        if (factory == null) {
            throw new IllegalArgumentException("Invalid theater type");
        }
        return factory.createTheater();
    }

    @Override
    public Theater getSpecificTheater(int id) {
        return theaterRepository.findById(id).orElseThrow();
    }


    @Override
    public List<Theater> getTheaters() {
        return null;
    }
}
