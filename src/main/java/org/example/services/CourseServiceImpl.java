package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.data.dtos.requests.CreateRequests.CreateCourseRequest;
import org.example.data.dtos.responses.CreateResponses.CreateCourseResponse;
import org.example.data.dtos.responses.FindResponses.FindCourseResponse;
import org.example.data.models.Course;
import org.example.data.models.Teacher;
import org.example.data.repositories.CourseRepository;
import org.example.exceptions.CourseNotFoundException;
import org.example.exceptions.UnauthorizedActionException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;

    @Override
    public CreateCourseResponse createCourse(Teacher teacher, CreateCourseRequest courseRequest) {
        Course courseToBeCreated = Course.builder()
                .courseCode(courseRequest.getCourseCode())
                .courseDescription(courseRequest.getCourseDescription())
                .courseName(courseRequest.getCourseName())
                .isPublic(courseRequest.getIsPublic())
                .teacher(teacher)
                .build();
        Course savedCourse = courseRepository.save(courseToBeCreated);
        return CreateCourseResponse.builder()
                .courseCode(savedCourse.getCourseCode())
                .courseDescription(savedCourse.getCourseDescription())
                .courseName(savedCourse.getCourseName())
                .teacherName(savedCourse.getTeacher().getFirstName() + " " + savedCourse.getTeacher().getLastName())
                .id(savedCourse.getId())
                .isPublic(savedCourse.isPublic())
                .teacherId(savedCourse.getTeacher().getId())
                .topics(savedCourse.getTopics())
                .build();
    }

    @Override
    public FindCourseResponse findCourseByTeacherAndId(Long teacherId, Long courseId) {
        List<Course> foundCourses = courseRepository.findByTeacher_Id(teacherId);
        if (foundCourses.isEmpty()) throw new CourseNotFoundException("You have not created any courses!");
        Course neededCourse = null;
        for (Course course : foundCourses) {
            if (course.getId().equals(courseId)) {
                neededCourse = course;
                break;
            }
        }
        if (neededCourse == null) throw new CourseNotFoundException("Cannot find the course you searched!");
        return getFindCourseResponse(neededCourse);
    }

    @Override
    public FindCourseResponse findCourseById(Long courseId) {
        Course foundCourse = courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new);
        return getFindCourseResponse(foundCourse);
    }

    @Override
    public FindCourseResponse findCourseByTeacherAndCourseCode(Long teacherId, String courseCode) {
        List<Course> foundCourses = courseRepository.findByTeacher_Id(teacherId);
        if (foundCourses.isEmpty()) throw new CourseNotFoundException("You have not created any courses!");
        Course neededCourse = null;
        for (Course course : foundCourses) {
            if (course.getCourseCode().equals(courseCode)) {
                neededCourse = course;
                break;
            }
        }
        if (neededCourse == null) throw new CourseNotFoundException("Cannot find the course you searched!");
        return getFindCourseResponse(neededCourse);
    }

    @Override
    public FindCourseResponse findCourseByCourseCode(String courseCode) {
        Course foundCourse = courseRepository.findCourseByCourseCode(courseCode).orElseThrow(CourseNotFoundException::new);
        return getFindCourseResponse(foundCourse);
    }

    private FindCourseResponse getFindCourseResponse(Course foundCourse) {
        int numberOfStudents = studentService.findStudentsByCourse(foundCourse).size();
        return FindCourseResponse.builder()
                .id(foundCourse.getId())
                .courseName(foundCourse.getCourseName())
                .courseDescription(foundCourse.getCourseDescription())
                .teacherId(foundCourse.getTeacher().getId())
                .teacherName(foundCourse.getTeacher().getFirstName() + " " + foundCourse.getTeacher().getLastName())
                .courseCode(foundCourse.getCourseCode())
                .topics(foundCourse.getTopics())
                .numberOfTopics(foundCourse.getTopics().size())
                .numberOfRegisteredStudents(numberOfStudents)
                .build();
    }

    @Override
    public void deleteCourseById(Long teacherId, Long courseId) {
        Course foundCourse = courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new);
        if (foundCourse.getTeacher().getId().equals(teacherId)) {
            courseRepository.delete(foundCourse);
        }
        else throw new UnauthorizedActionException("You are not authorized to delete this course.");
    }

    @Override
    public void deleteCourseByCourseCode(Long teacherId, String courseCode) {
        Course foundCourse = courseRepository.findCourseByCourseCode(courseCode).orElseThrow(CourseNotFoundException::new);
        if (foundCourse.getTeacher().getId().equals(teacherId)) {
            courseRepository.delete(foundCourse);
        }
        else throw new UnauthorizedActionException("You are not authorized to delete this course.");
    }

    @Override
    public List<Course> findCourseForTeacher(Long teacherId) {
        return courseRepository.findByTeacher_Id(teacherId);
    }

    @Override
    public HttpStatus registerStudent(String studentEmailAddress, String courseCode) {
        Course foundCourse = courseRepository.findCourseByCourseCode(courseCode).orElseThrow(CourseNotFoundException::new);
        studentService.addCourse(studentEmailAddress, foundCourse);
        return HttpStatus.OK;
    }

}
