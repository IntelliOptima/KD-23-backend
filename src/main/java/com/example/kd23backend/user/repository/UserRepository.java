package com.example.kd23backend.user.repository;

import com.example.kd23backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
