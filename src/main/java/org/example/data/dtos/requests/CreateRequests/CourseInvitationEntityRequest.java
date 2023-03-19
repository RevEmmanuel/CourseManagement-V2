package org.example.data.dtos.requests.CreateRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.models.Course;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseInvitationEntityRequest {

    @NotNull
    private String token;
    @Email
    @NotNull
    private String emailAddressOfStudent;
    @NotNull
    private Long teacherId;
    @NotNull
    private String courseCode;

}
