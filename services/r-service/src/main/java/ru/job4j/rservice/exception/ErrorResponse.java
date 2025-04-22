package ru.job4j.rservice.exception;

public record ErrorResponse(int statusCode, String message) {
}
