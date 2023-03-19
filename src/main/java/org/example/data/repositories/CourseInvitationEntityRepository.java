package org.example.data.repositories;

import org.example.data.models.CourseInvitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CourseInvitationEntityRepository extends JpaRepository<CourseInvitationEntity, Long> {

    Optional<CourseInvitationEntity> findByToken(String token);

    List<CourseInvitationEntity> findByTeacher_Id(Long teacherId);

}
