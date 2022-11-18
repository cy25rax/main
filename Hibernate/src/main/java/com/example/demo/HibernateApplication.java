package com.example.demo;

import clases.Product;
import clases.ProductDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HibernateApplication.class, args);
		ProductDao productDao = new ProductDao();


//		productDao.addProduct(new Product("bmw", 135));
//		System.out.println(productDao.findById(2));

//		System.out.println(productDao.findAll());
		productDao.Update(2, 10);


		productDao.getSessionFactory().close();

	}

}
