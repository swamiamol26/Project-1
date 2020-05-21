package com.msstudent.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.msstudent.api.model.Student;
import com.msstudent.api.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;

	@PostMapping("/student")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		Student student1 = studentService.saveStudent(student);
		return new ResponseEntity<Student>(student1, HttpStatus.OK);
	}

	@PutMapping("/student")
	public ResponseEntity<String> UpdateStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
		return new ResponseEntity<String>("student is Updated successfully", HttpStatus.OK);
	}

	@GetMapping("/student1/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Long id) {
		Student student = studentService.getStudent(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);

	}

	@GetMapping("/student2")
	public ResponseEntity<Student> getStudentByRequestParam(@RequestParam String name) {
		Student student = studentService.getStudentByName(name);
		return new ResponseEntity<Student>(student, HttpStatus.OK);

	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

	@DeleteMapping("/student/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<String>("student is  deleted successfully", HttpStatus.OK);

	}

}
