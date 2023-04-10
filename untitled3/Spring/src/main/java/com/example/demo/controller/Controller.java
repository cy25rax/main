package com.example.demo.controller;

import com.example.demo.models.Student;
import com.example.demo.services.StudentRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
	
	@Autowired
	private StudentRepositoryService service;
	
	@GetMapping("/findAll")
	public List<Student> findAll () {
		return service.findAll();
	}
	
	@GetMapping("/update/{id}")
	public void updateStudent (@PathVariable Long id,
							   @RequestParam(required = false) String name,
							   @RequestParam(required = false) Integer age) {
		service.saveStudent(id, name , age);
	}
	
	@GetMapping("/delete/{id}")
	public void deleteStudent (@PathVariable Long id){
		service.deleteStudent(id);
	}
	
	@GetMapping("addStudent")
	public void addStudent (@RequestParam String name,
							@RequestParam Integer age) {
		service.addStudent(name, age);
	}
}
