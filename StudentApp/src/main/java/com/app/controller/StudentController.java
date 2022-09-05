package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Student;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private IStudentService service;

	@PostMapping("/add")
	String addStudent(@RequestBody Student std) {
		Integer id = service.addStudent(std);
		return "Successfully added a student with id " + id;
	}

	@GetMapping("/get/{id}")
	Student getStudent(@RequestParam Integer id) {
		return service.getStudentById(id);
	}

	@GetMapping("/getAll/{page}/{size}")
	List<Student> getAllStudent(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
		Page<Student> resultPage = service.getAllStudents(PageRequest.of(page.orElse(0), size.orElse(5)));
		return resultPage.getContent();
	}
	
	@PutMapping("/update")
	String updateStudent(@RequestBody Student std) {
		service.updateStudent(std);
		return "Successfully updated a student with id " + std.getId();
	}

	@DeleteMapping("/delete/{id}")
	String deleteStudent(@RequestParam Integer id) {
		service.deleteStudentById(id);
		return "Successfully deleted a student with id " + id;
	}

	@GetMapping("/hello")
	String helloUser() {
		return "Hello, User!";
	}

}
