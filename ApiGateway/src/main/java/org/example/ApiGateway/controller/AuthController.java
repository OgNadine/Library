package org.example.apigateway.controller;

import org.example.apigateway.dto.UserDTO;
import org.example.apigateway.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        String token = authenticationService.authenticate(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        authenticationService.registerUser(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }
}
