package org.example.data.dtos.requests.CreateRequests;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
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
public class CreateCourseRequest {

    @NotNull(message = "Input cannot be empty.")
    private String courseCode;
    @NotNull(message = "Input cannot be empty.")
    @AssertTrue(message = "isPublic must be true or false")
    private Boolean isPublic;
    @NotNull(message = "Input cannot be empty.")
    private String courseName;
    private String courseDescription;

}
