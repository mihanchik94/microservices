package ru.job4j.rservice.util;

public final class ValidationConstants {

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String REGEXP_VALIDATE_GRADE_BOOK_NUMBER = "^\\d{2}/(?!000)\\d{3}$";
}
