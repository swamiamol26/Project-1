package com.msstudent.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msstudent.api.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	public Course findByTitle(String title);

}
