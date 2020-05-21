package com.msstudent.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "students")
public class Student implements Serializable {
	@Id
	private Long id;

	private String name;

	private String email;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_courses", joinColumns = {
			@JoinColumn(name = "student_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "course_id", referencedColumnName = "id") })
	 @JsonIgnoreProperties("students")
	private List<Course> courses;

	public Student() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
