package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends CourseManagementApplicationException {

    public InvalidTokenException() {
        this("You have provided an invalid token!");
    }

    public InvalidTokenException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

    public InvalidTokenException(String message, HttpStatus status) {
        super(message, status);
    }

}
