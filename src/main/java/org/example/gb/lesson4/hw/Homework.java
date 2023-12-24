package org.example.gb.lesson4.hw;

import java.sql.*;

public class Homework {

  /**
   * Задания необходимо выполнять на ЛЮБОЙ СУБД (postgres, mysql, sqlite, h2, ...)
   * 1. С помощью JDBC выполнить:
   * 1.1 Создать таблицу book с колонками id bigint, name varchar, author varchar, ...
   * 1.2 Добавить в таблицу 10 книг
   * 1.3 Сделать запрос select from book where author = 'какое-то имя' и прочитать его с помощью ResultSet
   */

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "user";
    private static final String password = "root";

    public static void main(String args[]) {

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement()) {

            prepareTables(con);
            insertData(con);

            System.out.println("find method by ResultSet");
            findBook(con, "book_2");

            System.out.println("find method by PreparedStatement");
            find2Book(con, "book_1");

            findAll(con);

            stmt.execute("""
                    drop table books;
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void findAll(Connection connection) {
        try (ResultSet rs = connection
                .createStatement()
                .executeQuery("select * from books")) {
            System.out.println("\nAll books:\n");
            while (rs.next()) {
                System.out.printf("book id = %d, name = %s, author = %s\n",
                        rs.getInt(1),
                        rs.getString("name"),
                        rs.getString("author"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void find2Book(Connection connection, String bookName) {
        try (PreparedStatement statement = connection.prepareStatement("select * from books where name = ?")){
            statement.setString(1, bookName);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("book id = %d, name = %s, author = %s\n",
                        resultSet.getInt(1),
                        resultSet.getString("name"),
                        resultSet.getString("author"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void findBook(Connection connection, String bookName) {
        try (ResultSet rs = connection
                .createStatement()
                .executeQuery("select * from books where name = '"+ bookName + "'")) {
            while (rs.next()) {
                System.out.printf("book id = %d, name = %s, author = %s\n",
                        rs.getInt(1),
                        rs.getString("name"),
                        rs.getString("author"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void prepareTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(" create table if not exists books(" +
                    "id bigint," +
                    "name varchar(255)," +
                    "author varchar(255));");
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
        insert into books(id, name, author)
        values(1, 'book_1', 'author_1'),
              (2, 'book_2', 'author_2'),
              (3, 'book_3', 'author_3'),
              (4, 'book_4', 'author_4'),
              (5, 'book_5', 'author_5');
              """);
        }
    }

}
