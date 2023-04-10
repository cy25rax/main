package com.example.demo.services;

import com.example.demo.models.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentRepositoryService {
	
	@Autowired
	private StudentRepository repository;
	
	public List<Student> findAll () {
		return repository.findAll();
	}
	
	public Student findById (Long id) {
		return repository.getReferenceById(id);
	}
	
	public void saveStudent (Long id, String name, Integer age) {
		Student student = repository.getReferenceById(id);
		if (age != null) student.setAge(age);
		if (name != null) student.setName(name);
		repository.save(student);
	}
	
	public void deleteStudent (Long id) {
		repository.deleteById(id);
	}
	
	public void addStudent (String name, Integer age) {
		Student student = new Student();
		student.setName(name);
		student.setAge(age);
		repository.save(student);
	}
}
