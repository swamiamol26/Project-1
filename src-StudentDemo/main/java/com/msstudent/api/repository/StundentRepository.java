package com.msstudent.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msstudent.api.model.Student;


public interface StundentRepository extends JpaRepository<Student, Long> {

	public Student findByName(String name);

}
