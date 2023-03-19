package org.example.data.repositories;

import org.example.data.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCourseByCourseCode(String courseCode);

    List<Course> findByTeacher_Id(Long teacherId);

}
