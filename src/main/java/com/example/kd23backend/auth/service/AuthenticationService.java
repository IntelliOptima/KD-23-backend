package com.example.kd23backend.auth.service;


import com.example.kd23backend.auth.dao.request.SignInRequest;
import com.example.kd23backend.auth.dao.request.SignUpRequest;
import com.example.kd23backend.auth.dao.response.JwtAuthenticationResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request, HttpServletResponse response);
}
