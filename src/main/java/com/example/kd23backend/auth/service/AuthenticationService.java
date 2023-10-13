package com.example.kd23backend.auth.service;


import com.example.kd23backend.auth.model.AuthenticationRequest;
import com.example.kd23backend.auth.model.AuthenticationResponse;
import com.example.kd23backend.auth.model.RegisterRequest;
import com.example.kd23backend.auth.model.Role;
import com.example.kd23backend.auth.model.Login;
import com.example.kd23backend.auth.repository.LoginRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final LoginRepo loginRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest registerRequest) {

        Login login = new Login();
        login.setEmail(registerRequest.getEmail());
        login.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        login.setRole(Role.USER);
        loginRepo.save(login);
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

        var user = loginRepo.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        System.out.println(user);
        var jwtToken = jwtService.generateToken(user);


        Cookie jwtCookie = new Cookie("token", jwtToken);
        Cookie roleCookie = new Cookie("Role", user.getRole().name());
        response.addCookie(jwtCookie);
        response.addCookie(roleCookie);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
