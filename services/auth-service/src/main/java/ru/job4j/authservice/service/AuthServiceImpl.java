package ru.job4j.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.authservice.dto.AuthRequest;
import ru.job4j.authservice.model.UserCredential;
import ru.job4j.authservice.repository.UserCredentialRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserCredentialRepository userCredentialRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public String saveUser(UserCredential user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userCredentialRepository.save(user);
        return String.format("user %s added to system",  user.getUsername());
    }

    @Override
    public String generateToken(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("Invalid access");
        }

    }

    @Override
    public String validateToken(String token) {
        return jwtService.validateToken(token);
    }
}
