import java.sql.*;

public class HomeWork302BD {

    public static void main(String[] args) {
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:javadb.db")) {
//                insertEx(connection, "login1", "pass1", "nick1");
//                deleteEx(connection);
                updateEx("123", "13");
//                readBase(connection);
//                System.out.println(readNickByLogin(connection,"login2","pass2"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    private static void readBase(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM database;")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("login") + " " +
                        rs.getString(3)+" "+ rs.getString(4));
            }
        }
    }
    private static String readNickByLogin(Connection connection,String login, String pass) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM database WHERE login = ? AND pass = ?;")) {
            statement.setString(1, login);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.getString("nick");
        }
    }
    private static void clearTableEx(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement ("DELETE FROM database;")) {
            statement.executeUpdate();
        }
    }
    private static void deleteEx(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
          ("DELETE FROM database WHERE login = 'login1';")) {
            statement.executeUpdate();
          }
    }
    private static void updateEx(String nick, String chgNick) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:javadb.db")) {
            try (PreparedStatement statement = connection.prepareStatement
                    ("UPDATE database SET nick = ? WHERE nick = ?;")) {
                statement.setString(1, chgNick);
                statement.setString(2, nick);
                statement.executeUpdate();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void insertEx(Connection connection, String login, String pass, String nick) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO database (login, pass, nick) VALUES (?, ?, ?);")) {
            statement.setString(1, login);
            statement.setString(2, pass);
            statement.setString(3, nick);
            statement.executeUpdate();
        }
    }

}
