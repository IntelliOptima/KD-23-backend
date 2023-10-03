package com.example.kd23backend.config;

import com.example.kd23backend.auth.config.ApplicationConfig;
import com.example.kd23backend.auth.model.Login;
import com.example.kd23backend.auth.model.Role;
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


        Login login = new Login();
        login.setEmail("test@test.dk");
        login.setPassword(applicationConfig.passwordEncoder().encode("Test1234"));
        login.setRole(Role.USER);
        userRepo.save(login);


    }
}
