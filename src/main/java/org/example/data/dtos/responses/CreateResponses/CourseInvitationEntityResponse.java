package org.example.data.dtos.responses.CreateResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.models.Course;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseInvitationEntityResponse {

    private String token;
    private String emailAddressOfStudent;
    private Long teacherId;
    private String courseCode;
    private HttpStatus status;
    private String message;

}
