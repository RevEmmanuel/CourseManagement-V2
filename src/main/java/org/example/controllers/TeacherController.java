package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
import org.example.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    private TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "Get A Particular Teacher by the teacher's email address",
            description = "Returns a Response entity containing the requested teacher and HTTP status code")
    @GetMapping("/findEmail/{email}")
    public ResponseEntity<?> getTeacherByEmail(
            @PathVariable
            @Parameter(name = "email", description = "The email address of the required teacher",
                    required = true, example = "user@example.com") @Valid @Email
            String email) {
        FindTeacherResponse foundTeacher = teacherService.findTeacherByEmail(email);
//        return ResponseEntity.status(HttpStatus.OK).body(foundStudent);
        return new ResponseEntity<>(foundTeacher, HttpStatus.OK);
    }

    @Operation(summary = "Get A Particular Teacher by the teacher's Id",
            description = "Returns a Response entity containing the requested teacher and HTTP status code")
    @GetMapping("find/{teacherId}")
    public ResponseEntity<?> getTeacherById(
            @PathVariable
            @Parameter(name = "id", description = "The id of the required teacher",
                    required = true, example = "1") @Valid @NotNull(message = "Input cannot be null")
            Long teacherId) {
        FindTeacherResponse foundTeacher = teacherService.findTeacherById(teacherId);
//        return ResponseEntity.status(HttpStatus.OK).body(foundStudent);
        return new ResponseEntity<>(foundTeacher, HttpStatus.OK);
    }

    @Operation(summary = "Create a new teacher",
            description = "Returns a Response entity containing the new teacher's details and HTTP status code")
    @PostMapping("/create")
    public ResponseEntity<?> createTeacher(
            @RequestBody
            @Parameter(name = "CreateTeacherRequest", required = true,
                    description = "Contains the details required to create a new teacher which are e-mail, password, first name, lastname and phone number.")
            @Valid CreateTeacherRequest teacherRequest) {
        CreateTeacherResponse response = teacherService.createTeacher(teacherRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an existing teacher",
            description = "Returns a Response entity HTTP status code")
    @DeleteMapping("delete/{teacherId}")
    public ResponseEntity<?> deleteTeacher(
            @PathVariable
            @Parameter(name = "teacherId", required = true, example = "1",
                    description = "The Id of the teacher to delete")
            @Valid @NotNull(message = "Input cannot be null")
            Long teacherId) {
        teacherService.deleteTeacherById(teacherId);
//        return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update the details of an existing teacher",
            description = "Returns a Response entity containing the updated teacher's details and HTTP status code")
    @PutMapping("/update")
    public ResponseEntity<?> updateTeacherDetails(
            @RequestBody
            @Parameter(name = "updateTeacherRequest", required = true,
                    description = "Contains the details of the teacher that requires the update")
            @Valid
            UpdateTeacherDetailsRequest updateTeacherRequest) {
        UpdateTeacherDetailsResponse updateTeacherDetailsResponse = teacherService.updateTeacherDetails(updateTeacherRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(updateUserResponse);
        return new ResponseEntity<>(updateTeacherDetailsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get all teachers",
            description = "Returns a Response entity containing all existing teachers.")
    @GetMapping("/")
    public ResponseEntity<?> findAllTeachers() {
        List<Teacher> teachers = teacherService.findAllTeachers();

//        return ResponseEntity.status(HttpStatus.OK).body(students);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @Operation(summary = "Get all courses a teacher has created",
            description = "Returns a Response entity containing all existing courses of a teacher.")
    @GetMapping("/{teacherId}/courses")
    public ResponseEntity<?> findAllCoursesForTeacher(
            @PathVariable
            @Parameter(name = "teacherId", required = true, example = "1",
                    description = "The Id of the teacher whose courses are required")
            @Valid @NotNull(message = "Input cannot be null")
            Long teacherId
    ) {
        FindCourseForTeacherResponse foundCourses = teacherService.getCoursesForTeacher(teacherId);

//        return ResponseEntity.status(HttpStatus.OK).body(foundCourses);
        return new ResponseEntity<>(foundCourses, HttpStatus.OK);
    }

    @Operation(summary = "Create a new course",
            description = "The teacher creates a new course. Returns a Response entity containing the new course's details and HTTP status code")
    @PostMapping("/{teacherId}/createCourse")
    public ResponseEntity<?> createCourse(
            @PathVariable
            @Parameter(name = "teacherId", required = true, example = "1",
                    description = "The Id of the teacher who is creating the course")
            @Valid @NotNull(message = "Input cannot be null")
            Long teacherId,
            @RequestBody
            @Parameter(name = "CreateTeacherRequest", required = true,
                    description = "Contains the details required to create a new course which are course code, course name, description and publicity")
            @Valid CreateCourseRequest courseRequest) {
        CreateCourseResponse response = teacherService.createCourse(teacherId ,courseRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all tokens a teacher has created",
            description = "Returns a Response entity containing all existing invite tokens of a teacher.")
    @GetMapping("/{teacherId}/tokens")
    public ResponseEntity<?> findAllTokensForTeacher(
            @PathVariable
            @Parameter(name = "teacherId", required = true, example = "1",
                    description = "The Id of the teacher whose invite tokens are required")
            @Valid @NotNull(message = "Input cannot be null")
            Long teacherId
    ) {
        FindTokensForTeacherResponse foundTokens = teacherService.findTokensForTeacher(teacherId);

//        return ResponseEntity.status(HttpStatus.OK).body(foundTokens);
        return new ResponseEntity<>(foundTokens, HttpStatus.OK);
    }

    @Operation(summary = "Create a new invite token",
            description = "Returns a Response entity containing the new token's details and HTTP status code")
    @PostMapping("/createToken")
    public ResponseEntity<?> createToken(
            @RequestBody
            @Parameter(name = "CourseInvitationEntityRequest", required = true,
                    description = "Contains the details required to create a new token which are the token, teacher ID, email address of student and the course code")
            @Valid CourseInvitationEntityRequest entityRequest) {
        CourseInvitationEntityResponse response = teacherService.createToken(entityRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
