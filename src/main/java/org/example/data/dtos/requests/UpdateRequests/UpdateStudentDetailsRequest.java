package org.example.data.dtos.requests.UpdateRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStudentDetailsRequest {

    @NotNull(message = "Input cannot be null")
    private Long id;
    @NotBlank(message = "Input cannot be empty")
    private String firstName;
    @NotBlank(message = "Input cannot be empty")
    private String lastName;
    @Email(message = "Please enter a valid email address")
    private String email;
}
