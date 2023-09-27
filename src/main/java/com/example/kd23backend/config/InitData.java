package com.example.kd23backend.config;

import com.example.kd23backend.auth.config.ApplicationConfig;
import com.example.kd23backend.auth.model.Role;
import com.example.kd23backend.auth.model.User;
import com.example.kd23backend.auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    ApplicationConfig applicationConfig;

    @Autowired
    UserRepo userRepo;




    @Override
    public void run(String... args) throws Exception {


        User user = new User();
        user.setUsername("test");
        user.setPassword(applicationConfig.passwordEncoder().encode("Test1234"));
        user.setRole(Role.USER);
        userRepo.save(user);


    }
}
