package org.example.homework.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ExchangeSenderApp {
    private static final String EXCHANGE_NAME = "directExchanger";

    public static void main(String[] argv) throws Exception {
    
        System.out.println("send msg: 'php message to php subscribers'");
        sendMsg("php message to php subscribers");
        Thread.sleep(4000);
        System.out.println("send msg: 'java message to java subscribers'");
        sendMsg("java message to java subscribers");

    }
    
    public static void sendMsg(String msg) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            String message;
    
            if (msg.startsWith("php")) {
                message = msg.substring(4);
                channel.basicPublish(EXCHANGE_NAME, "php", null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }
            
            if (msg.startsWith("java")) {
                message = msg.substring(5);
                channel.basicPublish(EXCHANGE_NAME, "java", null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}