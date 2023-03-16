package org.example.data.dtos.responses.UpdateResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeTeacherPasswordResponse {

    private Long id;
    private String response;
}
