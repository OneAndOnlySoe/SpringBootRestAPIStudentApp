package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.entity.Student;
import com.app.repo.StudentRepository;
import com.app.service.IStudentService;

@Service
public class StudentService implements IStudentService {

	@Autowired 
	private StudentRepository studentRepo;
	
	@Override
	public Integer addStudent(Student student) {
		return studentRepo.save(student).getId();
	}

	@Override
	public Student getStudentById(Integer id) {
		Optional<Student> student = studentRepo.findById(id);
		if(student.isEmpty())return null;
		return student.get();
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}
	
	public Page<Student> getAllStudents(Pageable page) {
		return studentRepo.findAll(page);
	}
	
	@Override
	public void updateStudent(Student student) {
		studentRepo.save(student);
		
	}

	@Override
	public void deleteStudentById(Integer id) {
		studentRepo.deleteById(id);
	}

}
