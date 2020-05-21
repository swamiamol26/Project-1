package com.msstudent.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.msstudent.api.exception.CourseNotFoundException;
import com.msstudent.api.exception.NoCourseFoundException;
import com.msstudent.api.exception.NullDataFOundInCourseException;
import com.msstudent.api.exception.StudentNotFoundException;
import com.msstudent.api.model.Course;
import com.msstudent.api.model.Student;
import com.msstudent.api.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	RestTemplate restTemplate;

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public String getAllStudents() {
		String result = restTemplate.getForObject("http://STUDENT-SERVICE/students", String.class);
		return result;
	}

	public String getStudentByPathVar(String id) {
		try {
			String result = restTemplate.getForObject("http://STUDENT-SERVICE/student1/" + id, String.class);
			return result;
		} catch (Exception e) {
			throw new StudentNotFoundException();
		}
	}

	public String getStudentsByReqParam(String name) {

		String url = "http://STUDENT-SERVICE/student2";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}

		String result = restTemplate.getForObject(builder.toUriString(), String.class);
		return result;
	}

	public ResponseEntity<String> SaveStudentByPostBody(Student student) {
		String uri = "http://STUDENT-SERVICE/student";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject request = new JSONObject();
		request.put("id", student.getId());
		request.put("name", student.getName());
		request.put("email", student.getEmail());
		request.put("courses", student.getCourses());

		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);
		System.out.println(response);
		return response;
	}

	public Course saveCourse(Course course) {
		if (course != null) {
			return courseRepository.save(course);
		} else {
			throw new NullDataFOundInCourseException();
		}
	}

	public Course getCourseById(Long id) {
		Optional<Course> course = courseRepository.findById(id);
		if (course.isPresent()) {
			return course.get();
		} else {
			throw new CourseNotFoundException();
		}

	}

	public Course getCourseByTitle(String title) {
		Course course = courseRepository.findByTitle(title);
		if (course == null) {
			throw new CourseNotFoundException();
		} else {
			return course;
		}

	}

	public void deleteCourseById(Long id) {
		courseRepository.deleteById(id);
	}

	public List<Course> getAllCourses() {

		List<Course> courses = courseRepository.findAll();
		if (courses.isEmpty()) {
			throw new NoCourseFoundException();
		}
		return courses;
	}

}
