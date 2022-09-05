package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.entity.Student;

public interface IStudentService {
	
	//add data
	Integer addStudent(Student student);
	//get data
	Student getStudentById(Integer id);
	//get all data
	List<Student> getAllStudents();
	//get all data
	Page<Student> getAllStudents(Pageable pageable);
	//update data
	void updateStudent(Student student);
	//delete data
	void deleteStudentById(Integer id);
}
