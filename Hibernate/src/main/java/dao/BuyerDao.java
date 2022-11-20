package dao;

import clases.Buyer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuyerDao {

    @Autowired
    private final SessionFactory sessionFactory;
    private Session session = null;

    public BuyerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addBuyer(Buyer buyer) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(buyer);
        session.getTransaction().commit();
    }

    public List<Buyer> findAll() {
        List<Buyer> list;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        list = session.createQuery("SELECT b FROM Buyer b", Buyer.class)
                .getResultList();
        session.getTransaction().commit();

        return list;
    }

    public Buyer findById (int id){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Buyer buyer = session.get(Buyer.class, id);
        session.getTransaction().commit();
        return buyer;
    }

    public void deleteById(int id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Buyer buyer = session.get(Buyer.class, id);
        session.remove(buyer);
        session.getTransaction().commit();
    }

    public void update (int id, String newName){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Buyer buyer = session.get(Buyer.class, id);
        buyer.setName(newName);
        session.getTransaction().commit();
    }

}
