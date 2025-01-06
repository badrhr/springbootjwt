package com.xproce.authapi.controllers;

import com.xproce.authapi.dtos.responses.CustomUser;
import com.xproce.authapi.dtos.LoginUserDto;
import com.xproce.authapi.dtos.RegisterUserDto;
import com.xproce.authapi.dtos.responses.LoginResponse;
import com.xproce.authapi.services.AuthenticationService;
import com.xproce.authapi.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<CustomUser> register(@RequestBody RegisterUserDto registerUserDto) {
        CustomUser registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        CustomUser authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();
        return ResponseEntity.ok(loginResponse);
    }
}