package org.example.services;

import org.example.data.dtos.requests.CreateRequests.CourseInvitationEntityRequest;
import org.example.data.dtos.responses.CreateResponses.CourseInvitationEntityResponse;
import org.example.data.dtos.responses.FindResponses.FindCourseInvitationStatus;
import org.example.data.models.CourseInvitationEntity;
import org.example.data.models.Teacher;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CourseInvitationEntityService {

    CourseInvitationEntityResponse createToken(Teacher teacher, CourseInvitationEntityRequest entityRequest);

    HttpStatus addStudentToCourse(String token);

    List<CourseInvitationEntity> findByTeacher(Long teacherId);
}
