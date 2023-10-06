package com.example.kd23backend.address.repository;

import com.example.kd23backend.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
