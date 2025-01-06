package com.xproce.authapi.services;

import com.xproce.authapi.dtos.LoginUserDto;
import com.xproce.authapi.dtos.RegisterUserDto;
import com.xproce.authapi.dtos.responses.CustomUser;
import com.xproce.authapi.dao.entities.Customer;
import com.xproce.authapi.dao.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public CustomUser signup(RegisterUserDto input) {
        Customer customer = Customer.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .fullName(input.getFullName())
                .build();
        customer = customerRepository.save(customer);
        return CustomUser.builder().customer(customer).build();
    }

    public CustomUser authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        Customer customer = customerRepository.findByEmail(input.getEmail()).orElseThrow();
        return CustomUser.builder().customer(customer).build();
    }

}
