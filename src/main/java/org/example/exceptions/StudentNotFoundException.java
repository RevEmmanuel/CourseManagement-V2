package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends CourseManagementApplicationException {

    public StudentNotFoundException() {
        this("Student not found!");
    }

    public StudentNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
