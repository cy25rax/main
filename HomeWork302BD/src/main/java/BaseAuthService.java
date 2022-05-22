import java.sql.*;

public class BaseAuthService extends Logger implements AuthService {

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }
    @Override
    public void updateEx(String nick, String chgNick) {
       try (Connection connection = DriverManager.getConnection("jdbc:sqlite:javadb.db")) {
           try (PreparedStatement statement = connection.prepareStatement
                   ("UPDATE database SET nick = ? WHERE nick = ?;")) {
               statement.setString(1, chgNick);
               statement.setString(2, nick);
               statement.executeUpdate();
           }
      }catch (Exception e) {
           getErrorLog().warn(e);
           e.printStackTrace();
       }
    }

    @Override
    public String getNickByLoginPass(String login, String pass) {

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:javadb.db")) {

            try (PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM database WHERE login = ? AND pass = ?;")) {
                statement.setString(1, login);
                statement.setString(2, pass);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.getString("nick");
            }
        } catch (Exception e) {
            getErrorLog().warn(e);
            e.printStackTrace();
        }

        return null;
    }

}
