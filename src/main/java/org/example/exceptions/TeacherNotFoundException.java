package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class TeacherNotFoundException extends CourseManagementApplicationException {

    public TeacherNotFoundException() {
        this("Teacher with details provided not found!");
    }

    public TeacherNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
