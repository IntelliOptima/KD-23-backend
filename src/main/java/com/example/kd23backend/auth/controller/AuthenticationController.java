package com.example.kd23backend.auth.controller;


import com.example.kd23backend.auth.dao.request.SignInRequest;
import com.example.kd23backend.auth.dao.request.SignUpRequest;
import com.example.kd23backend.auth.dao.response.JwtAuthenticationResponse;
import com.example.kd23backend.auth.model.AuthenticationRequest;
import com.example.kd23backend.auth.model.AuthenticationResponse;
import com.example.kd23backend.auth.model.RegisterRequest;
import com.example.kd23backend.auth.service.AuthenticationServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> register(
            @RequestBody SignUpRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.signup(registerRequest));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthenticationResponse> authenticate(
            @RequestBody SignInRequest authenticationRequest,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authenticationService.signin(authenticationRequest, response));
    }

}
