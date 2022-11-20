package dao;

import clases.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {

    @Autowired
    private final SessionFactory sessionFactory;
    private Session session = null;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public ProductDao() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public void addProduct(Product p) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
    }

    public List<Product> findAll() {
        List<Product> list;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        list = session.createQuery("SELECT p FROM Product p", Product.class)
                        .getResultList();
        session.getTransaction().commit();

        return list;
    }

    public Product findById (int id){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        return product;
    }

    public void deleteById(int id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.remove(product);
        session.getTransaction().commit();
    }

    public void Update (int id, String newTitle){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        product.setTitle(newTitle);
        session.getTransaction().commit();
    }

    public void Update (int id, int newCost){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        product.setCost(newCost);
        session.getTransaction().commit();
    }
}
