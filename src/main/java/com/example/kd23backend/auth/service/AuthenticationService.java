package com.example.kd23backend.auth.service;


import com.example.kd23backend.auth.model.AuthenticationRequest;
import com.example.kd23backend.auth.model.AuthenticationResponse;
import com.example.kd23backend.auth.model.RegisterRequest;
import com.example.kd23backend.auth.model.Role;
import com.example.kd23backend.auth.model.Login;
import com.example.kd23backend.auth.repository.UserRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest registerRequest) {

        Login login = new Login();
        login.setEmail(registerRequest.getEmail());
        login.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        login.setRole(Role.USER);
        userRepo.save(login);
        var jwtToken = jwtService.generateToken(login);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword())
        );

        var user = userRepo.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);


        Cookie jwtCookie = new Cookie("token", jwtToken);
        response.addCookie(jwtCookie);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
