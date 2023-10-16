package com.example.kd23backend.auth.service;

import com.example.kd23backend.auth.repository.LoginRepo;
import com.example.kd23backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final LoginRepo loginRepo;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> loginRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
