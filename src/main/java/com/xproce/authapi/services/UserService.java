package com.xproce.authapi.services;

import com.xproce.authapi.dtos.responses.CustomUser;
import com.xproce.authapi.dao.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final CustomerRepository customerRepository;

    public List<CustomUser> allUsers() {
        List<CustomUser> users = customerRepository.findAll()
                .stream()
                .map(customer -> CustomUser.builder().customer(customer).build())
                .collect(Collectors.toList());

        return users;
    }
}