package com.buythebook.bookstore.userservice.controller;

import com.buythebook.bookstore.userservice.dto.request.LoginRequest;
import com.buythebook.bookstore.userservice.dto.request.RegistrationRequest;
import com.buythebook.bookstore.userservice.dto.response.LoginResponse;
import com.buythebook.bookstore.userservice.dto.response.SuccessResponse;
import com.buythebook.bookstore.userservice.security.JwtTokenGenerator;
import com.buythebook.bookstore.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        userService.createUser(registrationRequest);
        return ResponseEntity.ok(new SuccessResponse("Registration successful", ""));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                        loginRequest.getPassword()));
        return ResponseEntity.ok(new LoginResponse("Bearer " + jwtTokenGenerator.generateJwtToken(authentication)));


    }
}
