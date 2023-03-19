package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.services.CourseInvitationEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class CourseInvitationController {

    private final CourseInvitationEntityService courseInvitationEntityService;

    @Autowired
    private CourseInvitationController(CourseInvitationEntityService courseInvitationEntityService) {
        this.courseInvitationEntityService = courseInvitationEntityService;
    }

    @Operation(summary = "Accept invite to a course",
            description = "Returns a Response entity containing the status of your invitation")
    @PostMapping("/invite/{inviteToken}")
    public ResponseEntity<?> acceptInvite(
            @PathVariable
            @Parameter(name = "invite token", required = true, example = "ChiToken",
                    description = "The token to accept")
            @Valid @NotNull(message = "Input cannot be null")
            String inviteToken) {
        HttpStatus response = courseInvitationEntityService.addStudentToCourse(inviteToken);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return new ResponseEntity<>("Invite accepted!", response);
    }

}
