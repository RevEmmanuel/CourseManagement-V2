package org.example.data.dtos.requests.UpdateRequests;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStudentDetailsRequest {

    private Long id;
    private String firstName;
    private String lastName;
    @Email(message = "Please enter a valid email address")
    private String email;
}
