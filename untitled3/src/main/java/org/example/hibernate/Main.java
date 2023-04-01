package org.example.hibernate;

public class Main {
	
	public static void main(String[] args) {
		try (StudentDAO studentDAO = new StudentDAO()){
//			studentDAO.fillDB();
			System.out.println(studentDAO.findAll());
			System.out.println(studentDAO.findByMark(66));
			System.out.println(studentDAO.countStudentsByMark(66));
			System.out.println(studentDAO.findById(2));
		}
	}
}
