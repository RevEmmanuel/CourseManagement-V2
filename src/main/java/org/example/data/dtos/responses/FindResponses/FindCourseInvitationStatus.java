package org.example.data.dtos.responses.FindResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindCourseInvitationStatus {

    private String token;
    private String emailAddressOfStudent;
    private Long teacherId;
    private String courseCode;
    private HttpStatus status;
    private String message;

}
