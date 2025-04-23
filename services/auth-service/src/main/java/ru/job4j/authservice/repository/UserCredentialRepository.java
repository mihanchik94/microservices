package ru.job4j.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.authservice.model.UserCredential;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
    Optional<UserCredential> findByUsername(String username);
}
