package org.example.services;

import org.example.data.dtos.requests.CreateRequests.CreateTeacherRequest;
import org.example.data.dtos.requests.UpdateRequests.UpdateTeacherDetailsRequest;
import org.example.data.dtos.responses.CreateResponses.CreateTeacherResponse;
import org.example.data.dtos.responses.FindResponses.FindTeacherResponse;
import org.example.data.dtos.responses.UpdateResponse.UpdateTeacherDetailsResponse;
import org.example.data.models.Teacher;
import java.util.List;
//import java.util.Set;

public interface TeacherService {

    CreateTeacherResponse createTeacher(CreateTeacherRequest createTeacherRequest);

    UpdateTeacherDetailsResponse updateTeacherDetails(UpdateTeacherDetailsRequest updateTeacherDetailsRequest);

    FindTeacherResponse findTeacherById(Long teacherId);

    FindTeacherResponse findTeacherByEmail(String emailAddress);

    void deleteTeacherById(Long teacherId);

//    Set<Course> getCoursesForStudent(Long teacherId);


    List<Teacher> findAllTeachers();
}
