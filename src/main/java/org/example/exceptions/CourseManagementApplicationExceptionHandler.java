package org.example.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CourseManagementApplicationExceptionHandler {

    @ExceptionHandler(CourseManagementApplicationException.class)
    public ResponseEntity<CourseManagementApplicationExceptionResponse> handleEmailApplicationException(CourseManagementApplicationException e){
        var res = CourseManagementApplicationExceptionResponse
                .builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .build();

        return new ResponseEntity<>(res, e.getStatus());
    }
}
