package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.example.data.dtos.requests.CreateRequests.CreateStudentRequest;
import org.example.data.dtos.requests.UpdateRequests.UpdateStudentDetailsRequest;
import org.example.data.dtos.responses.CreateResponses.CreateStudentResponse;
import org.example.data.dtos.responses.FindResponses.FindStudentResponse;
import org.example.data.dtos.responses.UpdateResponse.UpdateStudentDetailsResponse;
import org.example.data.models.Course;
import org.example.data.models.Student;
import org.example.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    private StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Get A Particular Student by the user's email address",
            description = "Returns a Response entity containing the requested student and HTTP status code")
    @GetMapping("/findEmail/{email}")
    public ResponseEntity<?> getStudentByEmail(
            @PathVariable
            @Parameter(name = "email", description = "The email address of the required student",
                    required = true, example = "user@example.com") @Valid @Email
            String email) {
        FindStudentResponse foundStudent = studentService.findStudentByEmail(email);
//        return ResponseEntity.status(HttpStatus.OK).body(foundStudent);
        return new ResponseEntity<>(foundStudent, HttpStatus.OK);
    }

    @Operation(summary = "Get A Particular Student by the student's Id",
            description = "Returns a Response entity containing the requested student and HTTP status code")
    @GetMapping("find/{studentId}")
    public ResponseEntity<?> getStudentById(
            @PathVariable
            @Parameter(name = "id", description = "The id of the required student",
                    required = true, example = "1") @Valid
            @NotNull(message = "Input cannot be null")
            Long studentId) {
        FindStudentResponse foundStudent = studentService.findStudentById(studentId);
//        return ResponseEntity.status(HttpStatus.OK).body(foundStudent);
        return new ResponseEntity<>(foundStudent, HttpStatus.OK);
    }

    @Operation(summary = "Create a new student",
            description = "Returns a Response entity containing the new student's details and HTTP status code")
    @PostMapping("/create")
    public ResponseEntity<?> createStudent(
            @RequestBody
            @Parameter(name = "CreateStudentRequest", required = true,
                    description = "Contains the details required to create a new student which are e-mail, password, first name and lastname.")
            @Valid CreateStudentRequest studentRequest) {
        CreateStudentResponse response = studentService.createStudent(studentRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an existing student",
            description = "Returns a Response entity HTTP status code")
    @DeleteMapping("delete/{studentId}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable
            @Parameter(name = "studentId", required = true, example = "1",
                    description = "The Id of the student to delete")
                    @Valid @NotNull(message = "Input cannot be null")
            Long studentId) {
        studentService.deleteStudentById(studentId);
//        return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update the details of an existing student",
            description = "Returns a Response entity containing the updated student's details and HTTP status code")
    @PutMapping("/update")
    public ResponseEntity<?> updateStudentDetails(
            @RequestBody
            @Parameter(name = "updateStudentRequest", required = true,
                    description = "Contains the details of the user that requires the update")
                    @Valid
            UpdateStudentDetailsRequest updateStudentRequest) {
        UpdateStudentDetailsResponse updateStudentDetailsResponse = studentService.updateStudentDetails(updateStudentRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(updateUserResponse);
        return new ResponseEntity<>(updateStudentDetailsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get the courses of an existing student",
            description = "Returns a Response entity containing the student's courses and HTTP status code")
    @GetMapping("/{studentId}/course")
    public ResponseEntity<?> getCoursesForStudent(
            @PathVariable
            @Parameter(name = "studentId", example = "1", required = true,
                    description = "The ID of the student whose courses is being retrieved.")
            @NotNull(message = "Input cannot be null") @Valid
            Long studentId) {
        Set<Course> courses = studentService.getCoursesForStudent(studentId);

//        return ResponseEntity.status(HttpStatus.OK).body(emails);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @Operation(summary = "Get all students",
            description = "Returns a Response entity containing all existing students.")
    @GetMapping("/")
    public ResponseEntity<?> findAllStudents() {
        List<Student> students = studentService.findAllStudents();

//        return ResponseEntity.status(HttpStatus.OK).body(students);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
