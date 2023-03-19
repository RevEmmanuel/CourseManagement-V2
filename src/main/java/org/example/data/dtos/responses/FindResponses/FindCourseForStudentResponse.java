package org.example.data.dtos.responses.FindResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.models.Course;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FindCourseForStudentResponse {

    private String firstName;
    private String lastName;
    private String email;
    private List<Course> courses;
}
