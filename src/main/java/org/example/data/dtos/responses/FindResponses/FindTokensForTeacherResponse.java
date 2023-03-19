package org.example.data.dtos.responses.FindResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.models.CourseInvitationEntity;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindTokensForTeacherResponse {

    private String firstName;
    private String lastName;
    private List<CourseInvitationEntity> tokens;
}
