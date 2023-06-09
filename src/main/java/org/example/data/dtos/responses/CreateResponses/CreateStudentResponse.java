package org.example.data.dtos.responses.CreateResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
