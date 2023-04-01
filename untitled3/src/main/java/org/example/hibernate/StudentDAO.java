package org.example.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class StudentDAO implements AutoCloseable {
	private static SessionFactory sessionFactory = null;
	private static Session session = null;
	
	public StudentDAO() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration()
										  .addAnnotatedClass(Student.class)
										  .buildSessionFactory();
		}
	}
	
	public void fillDB() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		for (int i = 0; i < 1000; i++) {
			Student student = new Student(
					"name_" + i,
					(int) (Math.random() * 100)
			);
			session.save(student);
		}
		session.getTransaction().commit();
	}
	
	public void addStudent(Student student) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
	}
	
	public List<Student> findAll() {
		List<Student> list;
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		list = session.createQuery("SELECT b FROM Student b", Student.class)
					   .getResultList();
		session.getTransaction().commit();
		
		return list;
	}
	
	public Student findById (int id){
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Student student = session.get(Student.class, id);
		session.getTransaction().commit();
		return student;
	}
	
	public void deleteById(int id) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Student buyer = session.get(Student.class, id);
		session.remove(buyer);
		session.getTransaction().commit();
	}
	
	public void update (int id, String newName){
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Student student = session.get(Student.class, id);
		student.setName(newName);
		session.getTransaction().commit();
	}
	
	public List<Student> findByMark (int mark) {
		List<Student> list;
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		list = session.createQuery("SELECT b FROM Student b WHERE b.mark="+mark, Student.class)
							  .getResultList();
		session.getTransaction().commit();
		return list;
	}
	
	public Long countStudentsByMark (int mark) {
		Long result;
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		result = session.createQuery("SELECT COUNT(b) FROM Student b WHERE b.mark="+mark, Long.class)
								.getSingleResult();
		session.getTransaction().commit();
		return result;
	}
	
	@Override
	public void close() {
		sessionFactory.close();
	}
}
