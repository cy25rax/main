package org.example.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "student_name")
	private String name;
	
	@Column
	private int mark;
	
	public Student() {
	}
	
	public Student(String name, int mark) {
		this.id = id;
		this.name = name;
		this.mark = mark;
	}
	
	@Override
	public String toString() {
		return "Student{" +
					   "id=" + id +
					   ", name='" + name + '\'' +
					   ", mark=" + mark +
					   '}';
	}
	
	public int getMark() {
		return mark;
	}
	
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
