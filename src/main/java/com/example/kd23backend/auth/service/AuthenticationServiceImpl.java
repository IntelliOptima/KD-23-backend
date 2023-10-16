package com.example.kd23backend.auth.service;


import com.example.kd23backend.auth.dao.request.SignInRequest;
import com.example.kd23backend.auth.dao.request.SignUpRequest;
import com.example.kd23backend.auth.dao.response.JwtAuthenticationResponse;
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
public class AuthenticationServiceImpl implements AuthenticationService {

    private final LoginRepo loginRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var userLogin = Login
                .builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER).build();

        loginRepo.save(userLogin);
        var jwt = jwtService.generateToken(userLogin);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var userLogin = loginRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        System.out.println(userLogin.getRole().name());
        var jwt = jwtService.generateToken(userLogin);

        Cookie jwtCookie = new Cookie("token", jwt);
        Cookie roleCookie = new Cookie("Role", userLogin.getRole().name());
        response.addCookie(jwtCookie);
        response.addCookie(roleCookie);

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
