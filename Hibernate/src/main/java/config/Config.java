package config;

import clases.Basket;
import clases.Buyer;
import clases.Product;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("dao")
public class Config {

    @Bean
    public SessionFactory sessionFactory(){
        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Buyer.class)
                .addAnnotatedClass(Basket.class)
                .buildSessionFactory();
        return sessionFactory;
    }
}
