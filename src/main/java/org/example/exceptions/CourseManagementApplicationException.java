package org.example.exceptions;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public class CourseManagementApplicationException extends RuntimeException{

    @Getter
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public CourseManagementApplicationException() {
        this("An error occurred");
    }

    public CourseManagementApplicationException(String message) {
        super(message);
    }

    public CourseManagementApplicationException(String message, HttpStatus status){
        this(message);
        this.status = status;
    }
}
