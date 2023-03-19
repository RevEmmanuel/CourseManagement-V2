package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.data.dtos.requests.CreateRequests.CreateStudentRequest;
import org.example.data.dtos.requests.UpdateRequests.UpdateStudentDetailsRequest;
import org.example.data.dtos.responses.CreateResponses.CreateStudentResponse;
import org.example.data.dtos.responses.FindResponses.FindCourseForStudentResponse;
import org.example.data.dtos.responses.FindResponses.FindStudentResponse;
import org.example.data.dtos.responses.UpdateResponse.UpdateStudentDetailsResponse;
import org.example.data.models.Course;
import org.example.data.models.Student;
import org.example.data.repositories.StudentRepository;
import org.example.exceptions.StudentNotFoundException;
import org.example.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public CreateStudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Student studentToBeCreated = Student.builder()
                .firstName(createStudentRequest.getFirstName())
                .lastName(createStudentRequest.getLastName())
                .email(createStudentRequest.getEmail())
                .password(createStudentRequest.getPassword())
                .build();
        Optional<Student> student = studentRepository.findStudentByEmail(createStudentRequest.getEmail());
        if (student.isPresent()) throw new UserAlreadyExistsException("Student with email address exists already!");
        Student newStudent = studentRepository.save(studentToBeCreated);
        return CreateStudentResponse.builder()
                .id(newStudent.getId())
                .firstName(newStudent.getFirstName())
                .lastName(newStudent.getLastName())
                .email(newStudent.getEmail())
                .build();
    }

    @Override
    public UpdateStudentDetailsResponse updateStudentDetails(UpdateStudentDetailsRequest updateStudentDetailsRequest) {
        Student studentToBeUpdated = studentRepository.findById(updateStudentDetailsRequest.getId()).orElseThrow(StudentNotFoundException::new);
        studentToBeUpdated.setFirstName(updateStudentDetailsRequest.getFirstName());
        studentToBeUpdated.setLastName(updateStudentDetailsRequest.getLastName());
        studentToBeUpdated.setEmail(updateStudentDetailsRequest.getEmail());
        studentRepository.save(studentToBeUpdated);
        studentToBeUpdated = studentRepository.findById(updateStudentDetailsRequest.getId()).orElseThrow(StudentNotFoundException::new);
        return UpdateStudentDetailsResponse.builder()
                .firstName(studentToBeUpdated.getFirstName())
                .lastName(studentToBeUpdated.getLastName())
                .email(studentToBeUpdated.getEmail())
                .id(studentToBeUpdated.getId())
                .build();
    }

    @Override
    public FindStudentResponse findStudentById(Long studentId) {
        Student foundStudent = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        return FindStudentResponse.builder()
                .firstName(foundStudent.getFirstName())
                .lastName(foundStudent.getLastName())
                .email(foundStudent.getEmail())
                .courses(foundStudent.getCourses())
                .id(foundStudent.getId())
                .build();
    }

    @Override
    public FindStudentResponse findStudentByEmail(String emailAddress) {
        Student foundStudent = studentRepository.findStudentByEmail(emailAddress).orElseThrow(StudentNotFoundException::new);
        return FindStudentResponse.builder()
                .firstName(foundStudent.getFirstName())
                .lastName(foundStudent.getLastName())
                .email(foundStudent.getEmail())
                .courses(foundStudent.getCourses())
                .id(foundStudent.getId())
                .build();
    }

    @Override
    public void deleteStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(student);
    }

    @Override
    public FindCourseForStudentResponse getCoursesForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Set<Course> courses = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new).getCourses();
        List<Course> coursesList = new ArrayList<>(courses);
        return FindCourseForStudentResponse.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .courses(coursesList)
                .build();
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findStudentsByCourse(Course course) {
        return studentRepository.findByCoursesContaining(course);
    }

    @Override
    public void addCourse(String studentEmailAddress, Course course) {
        Student foundStudent = studentRepository.findStudentByEmail(studentEmailAddress).orElseThrow(StudentNotFoundException::new);
        foundStudent.getCourses().add(course);
        studentRepository.save(foundStudent);
    }
}
