package org.example.data.dtos.requests.UpdateRequests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChangeStudentPasswordRequest {

    private Long id;
    @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace.")
    private String password;
}
