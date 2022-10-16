package com.geekbrains.netty;

import java.sql.*;

public class Auth {

    private static Connection connection;

    public static void connect () {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean auth (String login, String pass) {
        try (PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM database WHERE login = ? AND password = ?;")) {
            statement.setString(1, login);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            boolean result = resultSet.isClosed();
            System.out.println("bd " + result);
            return !result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
