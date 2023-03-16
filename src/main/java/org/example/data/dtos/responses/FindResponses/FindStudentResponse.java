package org.example.data.dtos.responses.FindResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.models.Course;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindStudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Course> courses = new HashSet<>();
}
