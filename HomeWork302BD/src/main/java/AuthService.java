public interface AuthService {
    void start();
    void updateEx(String nick, String nick1);
    String getNickByLoginPass(String login, String pass);
    void stop();
}
