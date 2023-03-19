package org.example.data.dtos.responses.CreateResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.models.Topic;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseResponse {

    private Long id;
    private String courseCode;
    private boolean isPublic;
    private String courseName;
    private String courseDescription;
    private Set<Topic> topics;
    private Long teacherId;
    private String teacherName;
}
