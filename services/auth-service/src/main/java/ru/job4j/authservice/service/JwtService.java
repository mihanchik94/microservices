package ru.job4j.authservice.service;

public interface JwtService {
    String generateToken(String username);
    String validateToken(String token);
}