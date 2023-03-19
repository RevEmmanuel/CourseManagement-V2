package org.example.services;

import org.example.data.dtos.requests.CreateRequests.CreateStudentRequest;
import org.example.data.dtos.requests.UpdateRequests.UpdateStudentDetailsRequest;
import org.example.data.dtos.responses.CreateResponses.CreateStudentResponse;
import org.example.data.dtos.responses.FindResponses.FindCourseForStudentResponse;
import org.example.data.dtos.responses.FindResponses.FindStudentResponse;
import org.example.data.dtos.responses.UpdateResponse.UpdateStudentDetailsResponse;
import org.example.data.models.Course;
import org.example.data.models.Student;
import java.util.List;

public interface StudentService {

    CreateStudentResponse createStudent(CreateStudentRequest createStudentRequest);

    UpdateStudentDetailsResponse updateStudentDetails(UpdateStudentDetailsRequest updateStudentDetailsRequest);

    FindStudentResponse findStudentById(Long studentId);

    FindStudentResponse findStudentByEmail(String emailAddress);

    void deleteStudentById(Long studentId);

    FindCourseForStudentResponse getCoursesForStudent(Long studentId);


    List<Student> findAllStudents();

    List<Student> findStudentsByCourse(Course course);

    void addCourse(String studentEmailAddress, Course course);
}
