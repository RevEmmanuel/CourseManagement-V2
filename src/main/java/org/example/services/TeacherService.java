package org.example.services;

import org.example.data.dtos.requests.CreateRequests.CourseInvitationEntityRequest;
import org.example.data.dtos.requests.CreateRequests.CreateCourseRequest;
import org.example.data.dtos.requests.CreateRequests.CreateTeacherRequest;
import org.example.data.dtos.requests.UpdateRequests.UpdateTeacherDetailsRequest;
import org.example.data.dtos.responses.CreateResponses.CourseInvitationEntityResponse;
import org.example.data.dtos.responses.CreateResponses.CreateCourseResponse;
import org.example.data.dtos.responses.CreateResponses.CreateTeacherResponse;
import org.example.data.dtos.responses.FindResponses.FindCourseForTeacherResponse;
import org.example.data.dtos.responses.FindResponses.FindTeacherResponse;
import org.example.data.dtos.responses.FindResponses.FindTokensForTeacherResponse;
import org.example.data.dtos.responses.UpdateResponse.UpdateTeacherDetailsResponse;
import org.example.data.models.Teacher;
import java.util.List;

public interface TeacherService {

    CreateTeacherResponse createTeacher(CreateTeacherRequest createTeacherRequest);

    UpdateTeacherDetailsResponse updateTeacherDetails(UpdateTeacherDetailsRequest updateTeacherDetailsRequest);

    FindTeacherResponse findTeacherById(Long teacherId);

    FindTeacherResponse findTeacherByEmail(String emailAddress);

    void deleteTeacherById(Long teacherId);

    FindCourseForTeacherResponse getCoursesForTeacher(Long teacherId);

    List<Teacher> findAllTeachers();

    CreateCourseResponse createCourse(Long teacherId, CreateCourseRequest courseRequest);

    FindTokensForTeacherResponse findTokensForTeacher(Long teacherId);

    CourseInvitationEntityResponse createToken(CourseInvitationEntityRequest entityRequest);
}
