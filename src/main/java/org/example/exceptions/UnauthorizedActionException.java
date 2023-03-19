package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedActionException extends CourseManagementApplicationException {

    public UnauthorizedActionException() {
        this("You do not have access to perform this action!");
    }

    public UnauthorizedActionException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
