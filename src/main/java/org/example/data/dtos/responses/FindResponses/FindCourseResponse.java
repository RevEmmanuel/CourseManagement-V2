package org.example.data.dtos.responses.FindResponses;

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
public class FindCourseResponse {

    private Long id;
    private String courseCode;
    private String courseName;
    private String courseDescription;
    private Set<Topic> topics;
    private Long teacherId;
    private String teacherName;
    private int numberOfRegisteredStudents;
    private int numberOfTopics;
}
