package org.example.gb.lesson4.hw;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

/**
 * 2. С помощью JPA(Hibernate) выполнить:
 * 2.1 Описать сущность Book из пункта 1.1
 * 2.2 Создать Session и сохранить в таблицу 10 книг
 * 2.3 Выгрузить список книг какого-то автора
 *
 * 3.* Создать сущность Автор (id biging, name varchar), и в сущности Book сделать поле типа Author (OneToOne)
 * 3.1 * Выгрузить Список книг и убедиться, что поле author заполнено
 * 3.2 ** В классе Author создать поле List<Book>, которое описывает список всех книг этого автора. (OneToMany)
 */
public class Hibernate {
    static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml").buildSessionFactory();

    public static void main(String[] args) throws SQLException {

        insertData(sessionFactory);
        changeAuthor(sessionFactory, 2, "new author");
        findBookByAuthorName(sessionFactory, "author_1");

        sessionFactory.close();
    }

    private static void findBookByAuthorName(SessionFactory sessionFactory, String newAuthor) {
        try (Session session = sessionFactory.openSession()) {
            Author author = session.createNamedQuery("Author.findAuthorByName", Author.class)
                    .setParameter("name", newAuthor)
                    .getSingleResultOrNull();
            List<Book> book = null;
            if (author !=null) {
                book = session.createNamedQuery("Book.findBookByAuthor", Book.class)
                        .setParameter("author", author.getId())
                        .getResultList();
            }
            System.out.println(book);
        }
    }

    private static void changeAuthor(SessionFactory sessionFactory, int id, String newAuthorName) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Book loadedBook = session.get(Book.class, id);
            Author author = session.createNamedQuery("Author.findAuthorByName", Author.class)
                            .setParameter("name", newAuthorName)
                            .getSingleResultOrNull();
            if (author == null) {
                author = new Author(newAuthorName);
                session.persist(author);
            }
            loadedBook.setAuthor(author);
            System.out.println(loadedBook);
            session.getTransaction().commit();
        }
    }

    private static void insertData(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Author author1 = new Author("author_1");
            Author author3 = new Author("author_3");
            Author author4 = new Author("author_4");
            Book book = new Book("book_1");
            Book book1 = new Book("book_2");
            Book book2 = new Book("book_3");
            Book book3 = new Book("book_4");

            session.persist(author1);
            session.persist(author4);
            session.persist(author3);

            book.setAuthor(author1);
            book1.setAuthor(author1);
            book2.setAuthor(author3);
            book3.setAuthor(author4);

            session.persist(book);
            session.persist(book1);
            session.persist(book2);
            session.persist(book3);

            session.getTransaction().commit();
        }
    }


}
