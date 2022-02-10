package com.realnet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	List<Course> findByInstructorId(Integer instructorId);
	Optional<Course> findByIdAndInstructorId(Integer courseId, Integer instructorId);
}