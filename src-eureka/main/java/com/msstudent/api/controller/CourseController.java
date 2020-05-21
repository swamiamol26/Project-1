package com.msstudent.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msstudent.api.model.Course;
import com.msstudent.api.model.Student;
import com.msstudent.api.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	CourseService courseService;

	@GetMapping("/courses")
	public String getAllStudents() {
		return courseService.getAllStudents();
	}

	@GetMapping("/course/{id}")
	public String getStudentByPathVar(@PathVariable String id) {
		return courseService.getStudentByPathVar(id);
	}

	@GetMapping("/course1")
	public String getStudentByRequestParam(@RequestParam String name) {
		return courseService.getStudentsByReqParam(name);
	}

	@PostMapping("/course2")
	public ResponseEntity<String> SaveStudentByPostBody(@RequestBody Student student) {
		return courseService.SaveStudentByPostBody(student);
	}

	@PostMapping("/course")
	public ResponseEntity<String> saveCourse(@RequestBody Course course) {
		courseService.saveCourse(course);
		return new ResponseEntity<String>("course is saved successfully", HttpStatus.OK);
	}

	@PutMapping("/course4")
	public ResponseEntity<String> UpdateCourse(@RequestBody Course course) {
		courseService.saveCourse(course);
		return new ResponseEntity<String>("course is Updated successfully", HttpStatus.OK);
	}

	@GetMapping("/course5/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
		Course course = courseService.getCourseById(id);
		return new ResponseEntity<Course>(course, HttpStatus.OK);

	}

	@GetMapping("/course6/{title}")
	public ResponseEntity<Course> getCourseByTitle(@PathVariable String title) {
		Course course = courseService.getCourseByTitle(title);
		return new ResponseEntity<Course>(course, HttpStatus.OK);

	}

	@GetMapping("/course7")
	public ResponseEntity<List<Course>> getAllCourese() {
		List<Course> courses = courseService.getAllCourses();
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);

	}

	@DeleteMapping("/course8/{id}")
	public ResponseEntity<String> deleteCourseById(@PathVariable Long id) {
		courseService.deleteCourseById(id);
		return new ResponseEntity<String>("course is deleted successfully", HttpStatus.OK);
	}

}
