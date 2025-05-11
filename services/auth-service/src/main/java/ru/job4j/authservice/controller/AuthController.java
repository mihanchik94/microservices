package ru.job4j.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.authservice.dto.AuthRequest;
import ru.job4j.authservice.model.UserCredential;
import ru.job4j.authservice.service.AuthService;

@Tag(name = "Authentication Controller", description = "API for working with authentication")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @Operation(summary = "Register User")
    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody UserCredential user) {
        return ResponseEntity.ok(authService.saveUser(user));
    }

    @Operation(summary = "Authenticate user")
    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.generateToken(authRequest));
    }

    @Operation(summary = "Validate user authentication token")
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        return ResponseEntity.ok(authService.validateToken(token));
    }
}
