package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.data.dtos.requests.CreateRequests.CreateCourseRequest;
import org.example.data.dtos.requests.CreateRequests.CreateTeacherRequest;
import org.example.data.dtos.requests.UpdateRequests.UpdateTeacherDetailsRequest;
import org.example.data.dtos.responses.CreateResponses.CreateCourseResponse;
import org.example.data.dtos.responses.CreateResponses.CreateTeacherResponse;
import org.example.data.dtos.responses.FindResponses.FindCourseForTeacherResponse;
import org.example.data.dtos.responses.FindResponses.FindTeacherResponse;
import org.example.data.dtos.responses.UpdateResponse.UpdateTeacherDetailsResponse;
import org.example.data.models.Course;
import org.example.data.models.Teacher;
import org.example.data.repositories.TeacherRepository;
import org.example.exceptions.TeacherNotFoundException;
import org.example.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseService courseService;

    @Override
    public CreateTeacherResponse createTeacher(CreateTeacherRequest createTeacherRequest) {
        Teacher teacherToBeCreated = Teacher.builder()
                .firstName(createTeacherRequest.getFirstName())
                .lastName(createTeacherRequest.getLastName())
                .email(createTeacherRequest.getEmail())
                .phoneNumber(createTeacherRequest.getPhoneNumber())
                .password(createTeacherRequest.getPassword())
                .build();
        Optional<Teacher> teacher = teacherRepository.findTeacherByEmail(createTeacherRequest.getEmail());
        if (teacher.isPresent()) throw new UserAlreadyExistsException("Teacher with email address exists already!");
        Teacher newTeacher = teacherRepository.save(teacherToBeCreated);
        return CreateTeacherResponse.builder()
                .id(newTeacher.getId())
                .firstName(newTeacher.getFirstName())
                .lastName(newTeacher.getLastName())
                .phoneNumber(newTeacher.getPhoneNumber())
                .email(newTeacher.getEmail())
                .build();
    }

    @Override
    public UpdateTeacherDetailsResponse updateTeacherDetails(UpdateTeacherDetailsRequest updateTeacherDetailsRequest) {
        Teacher teacherToBeUpdated = teacherRepository.findById(updateTeacherDetailsRequest.getId()).orElseThrow(TeacherNotFoundException::new);
        teacherToBeUpdated.setEmail(updateTeacherDetailsRequest.getEmail());
        teacherToBeUpdated.setFirstName(updateTeacherDetailsRequest.getFirstName());
        teacherToBeUpdated.setLastName(updateTeacherDetailsRequest.getLastName());
        teacherToBeUpdated.setPhoneNumber(updateTeacherDetailsRequest.getPhoneNumber());
        teacherToBeUpdated = teacherRepository.save(teacherToBeUpdated);
        return UpdateTeacherDetailsResponse.builder()
                .id(teacherToBeUpdated.getId())
                .firstName(teacherToBeUpdated.getFirstName())
                .lastName(teacherToBeUpdated.getLastName())
                .phoneNumber(teacherToBeUpdated.getPhoneNumber())
                .email(teacherToBeUpdated.getEmail())
                .build();
    }

    @Override
    public FindTeacherResponse findTeacherById(Long teacherId) {
        Teacher foundTeacher = teacherRepository.findById(teacherId).orElseThrow(TeacherNotFoundException::new);
        return FindTeacherResponse.builder()
                .id(foundTeacher.getId())
                .firstName(foundTeacher.getFirstName())
                .lastName(foundTeacher.getLastName())
                .email(foundTeacher.getEmail())
                .phoneNumber(foundTeacher.getPhoneNumber())
                .build();
    }

    @Override
    public FindTeacherResponse findTeacherByEmail(String emailAddress) {
        Teacher foundTeacher = teacherRepository.findTeacherByEmail(emailAddress).orElseThrow(TeacherNotFoundException::new);
        return FindTeacherResponse.builder()
                .id(foundTeacher.getId())
                .firstName(foundTeacher.getFirstName())
                .lastName(foundTeacher.getLastName())
                .email(foundTeacher.getEmail())
                .phoneNumber(foundTeacher.getPhoneNumber())
                .build();
    }

    @Override
    public void deleteTeacherById(Long teacherId) {
        Teacher foundTeacher = teacherRepository.findById(teacherId).orElseThrow(TeacherNotFoundException::new);
        teacherRepository.delete(foundTeacher);
    }

    @Override
    public FindCourseForTeacherResponse getCoursesForTeacher(Long teacherId) {
        Teacher foundTeacher = teacherRepository.findById(teacherId).orElseThrow(TeacherNotFoundException::new);
        List<Course> teacherCourses = courseService.findCourseForTeacher(teacherId);
        return FindCourseForTeacherResponse.builder()
                .firstName(foundTeacher.getFirstName())
                .lastName(foundTeacher.getLastName())
                .courses(teacherCourses)
                .build();
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public CreateCourseResponse createCourse(Long teacherId, CreateCourseRequest courseRequest) {
        Teacher foundTeacher = teacherRepository.findById(teacherId).orElseThrow(TeacherNotFoundException::new);
        return courseService.createCourse(foundTeacher, courseRequest);
    }
}
