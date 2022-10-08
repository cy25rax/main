package com.geekbrains.model;

public class LoginAndPass implements CloudMessage{

    private final String login;
    private final String password;

    public LoginAndPass(String login, String password) {
        this.password = password;
        this.login = login;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public MessageType getType() {
        return MessageType.LOGIN_PASSWORD;
    }
}
