package com.geekbrains.model;


public class Pass implements CloudMessage {

    boolean pass;

    public boolean isPass() {
        return pass;
    }
    public Pass(boolean pass) {
        this.pass = pass;
    }

    @Override
    public MessageType getType() {
        return MessageType.PASS;
    }

}
