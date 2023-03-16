package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends CourseManagementApplicationException {

    public UserAlreadyExistsException() {
        this("User with email address already exists!");
    }

    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
