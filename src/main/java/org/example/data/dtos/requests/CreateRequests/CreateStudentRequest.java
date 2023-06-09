package org.example.data.dtos.requests.CreateRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {

    @NotBlank(message = "Input cannot be empty")
    private String firstName;
    @NotBlank(message = "Input cannot be empty")
    private String lastName;
    @Email(message = "Please enter a valid email address")
    private String email;
    @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace.")
    private String password;
}
