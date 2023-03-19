package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class CourseNotFoundException extends CourseManagementApplicationException {

    public CourseNotFoundException() {
        this("Course not found!");
    }

    public CourseNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
