package org.example.exceptions;

public class InvalidCourseCodeException extends CourseManagementApplicationException {

    public InvalidCourseCodeException() {
        this("You have entered an invalid course code!");
    }

    public InvalidCourseCodeException(String message) {
        super(message);
    }
}
