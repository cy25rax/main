package com.geekbrains.model;


public class Pass implements CloudMessage {

    private final String login;
    private final String password;
    private boolean in = false;

    public String getLogin() {
        return login;
    }

    public boolean isIn() {
        return in;
    }

    public Object setIn(boolean in) {
        this.in = in;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Pass(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public MessageType getType() {
        return MessageType.PASS;
    }
}
