package org.example.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class CourseInvitationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private String emailAddressOfStudent;
    private Long creationTeacherId;
    private String courseCode;
    private final LocalDateTime timeCreated = LocalDateTime.now();
    private HttpStatus httpStatus;
    private String message;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Teacher teacher;

}
