package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.data.dtos.requests.CreateRequests.CourseInvitationEntityRequest;
import org.example.data.dtos.responses.CreateResponses.CourseInvitationEntityResponse;
import org.example.data.dtos.responses.FindResponses.FindCourseResponse;
import org.example.data.models.CourseInvitationEntity;
import org.example.data.models.Teacher;
import org.example.data.repositories.CourseInvitationEntityRepository;
import org.example.exceptions.InvalidTokenException;
import org.example.exceptions.UnauthorizedActionException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseInvitationEntityServiceImpl implements CourseInvitationEntityService{

    private final CourseService courseService;
    private final CourseInvitationEntityRepository courseInvitationEntityRepository;

    @Override
    public CourseInvitationEntityResponse createToken(Teacher teacher, CourseInvitationEntityRequest entityRequest) {
        FindCourseResponse courseResponse = courseService.findCourseByCourseCode(entityRequest.getCourseCode());
        if (!courseResponse.getTeacherId().equals(entityRequest.getTeacherId())) throw new UnauthorizedActionException("You are not authorized to create invites for this course.");
        CourseInvitationEntity entityToBeCreated = CourseInvitationEntity.builder()
                .emailAddressOfStudent(entityRequest.getEmailAddressOfStudent())
                .courseCode(entityRequest.getCourseCode())
                .token(entityRequest.getToken())
                .creationTeacherId(entityRequest.getTeacherId())
                .teacher(teacher)
                .build();
//        CourseInvitationEntity oldEntity = courseInvitationEntityRepository.findByToken("Chijvminvite").orElseThrow(InvalidTokenException::new);
//        oldEntity.setTeacher(teacher);
//        courseInvitationEntityRepository.save(oldEntity);
        CourseInvitationEntity savedEntity = courseInvitationEntityRepository.save(entityToBeCreated);
        savedEntity.setHttpStatus(HttpStatus.PROCESSING);
        savedEntity.setMessage("Processing...");
        courseInvitationEntityRepository.save(savedEntity);
        return CourseInvitationEntityResponse.builder()
                .emailAddressOfStudent(savedEntity.getEmailAddressOfStudent())
                .teacherId(savedEntity.getCreationTeacherId())
                .status(HttpStatus.CREATED)
                .courseCode(savedEntity.getCourseCode())
                .token(savedEntity.getToken())
                .message(savedEntity.getMessage())
                .build();
    }


    @Override
    public HttpStatus addStudentToCourse(String token) {
        CourseInvitationEntity entity = courseInvitationEntityRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException("This token has expired or does not exist!"));
        LocalDateTime timeCreated = entity.getTimeCreated();
        LocalDateTime presentTime = LocalDateTime.now();
        Duration duration = Duration.between(timeCreated, presentTime);
        if (duration.toHours() > 24) {
            entity.setHttpStatus(HttpStatus.NO_CONTENT);
            entity.setMessage("Token expired without user confirmation.");
            courseInvitationEntityRepository.save(entity);
            throw new InvalidTokenException("The provided token has expired!");
        }
        String studentEmailAddress = entity.getEmailAddressOfStudent();
        String courseCode = entity.getCourseCode();
        HttpStatus status = courseService.registerStudent(studentEmailAddress, courseCode);
        if (status == HttpStatus.OK) {
            entity.setHttpStatus(status);
            entity.setMessage("Student registration successful!");
            courseInvitationEntityRepository.save(entity);
        }
        return status;
    }

    @Override
    public List<CourseInvitationEntity> findByTeacher(Long teacherId) {
        return courseInvitationEntityRepository.findByTeacher_Id(teacherId);
    }
}
