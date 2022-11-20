import clases.Basket;
import clases.Buyer;
import clases.Product;
import dao.BuyerDao;
import dao.ProductDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

//@SpringBootApplication
public class HibernateApplication {

    public static void main(String[] args) {
//		SpringApplication.run(HibernateApplication.class, args);
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext("config");
//
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        ProductDao productDao = context.getBean(ProductDao.class);
        BuyerDao buyer = context.getBean(BuyerDao.class);

//        buyer.addBuyer(new Buyer("John"));
//        buyer.addBuyer(new Buyer("Maks"));
//        buyer.deleteById(2);

        System.out.println(buyer.findById(5));

//		productDao.addProduct(new Product("bmw", 175));
//		System.out.println(productDao.findById(2));

//		System.out.println(productDao.findAll());
//        productDao.Update(2, 10);

        sessionFactory.close();
    }
}