package org.example.data.dtos.responses.UpdateResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateStudentDetailsResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
