package ru.job4j.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Username is mandatory!")
    @Schema(defaultValue = "Username", description = "Username")
    private String username;

    @NotBlank(message = "Password is mandatory!")
    @Schema(defaultValue = "password", description = "Password")
    private String password;
}
