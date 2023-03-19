package org.example.data.dtos.requests.UpdateRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTeacherDetailsRequest {

    @NotNull(message = "Input cannot be null")
    private Long id;
    @NotBlank(message = "Input cannot be empty")
    private String firstName;
    @NotBlank(message = "Input cannot be empty")
    private String lastName;
    @Email(message = "Please enter a valid email address")
    private String email;
    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^(?:\\+234|0)(?:(?:80|81|70|81|90|91|81|70|81|81)\\d{8}|(?:708|802|903|905)\\d{7})$", message = "Invalid phone number format")
    private String phoneNumber;
}
