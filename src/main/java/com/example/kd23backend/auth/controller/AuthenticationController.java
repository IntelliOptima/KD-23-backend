package com.example.kd23backend.auth.controller;


import com.example.kd23backend.auth.model.AuthenticationRequest;
import com.example.kd23backend.auth.model.AuthenticationResponse;
import com.example.kd23backend.auth.model.RegisterRequest;
import com.example.kd23backend.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest, response));
    }

}
