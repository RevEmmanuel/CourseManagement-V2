package org.example.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class CourseManagementApplicationExceptionResponse {

    private String message;
    private HttpStatus status;
}
