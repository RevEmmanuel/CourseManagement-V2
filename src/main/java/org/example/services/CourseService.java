package org.example.services;

import org.example.data.dtos.requests.CreateRequests.CreateCourseRequest;
import org.example.data.dtos.responses.CreateResponses.CreateCourseResponse;
import org.example.data.dtos.responses.FindResponses.FindCourseResponse;
import org.example.data.models.Course;
import org.example.data.models.Teacher;
import java.util.List;

public interface CourseService {

    CreateCourseResponse createCourse(Teacher teacher, CreateCourseRequest courseRequest);
    FindCourseResponse findCourseByTeacherAndId(Long teacherId, Long courseId);
    FindCourseResponse findCourseById(Long courseId);
    FindCourseResponse findCourseByTeacherAndCourseCode(Long teacherId, String courseCode);
    FindCourseResponse findCourseByCourseCode(String courseCode);
    void deleteCourseById(Long teacherId, Long courseId);
    void deleteCourseByCourseCode(Long teacherId, String courseCode);

    List<Course> findCourseForTeacher(Long teacherId);

}
