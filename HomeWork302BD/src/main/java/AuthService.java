import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AuthService {
    void start();
    void updateEx(String nick, String nick1);
    String getNickByLoginPass(String login, String pass);
    void stop();
}
