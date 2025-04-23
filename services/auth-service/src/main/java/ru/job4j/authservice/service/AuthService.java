package ru.job4j.authservice.service;

import ru.job4j.authservice.dto.AuthRequest;
import ru.job4j.authservice.model.UserCredential;

public interface AuthService {
    String saveUser(UserCredential user);

    String generateToken(AuthRequest authRequest);

    String validateToken(String token);
}
