package org.example.homework.consumer;

import com.rabbitmq.client.*;

import java.util.Scanner;

public class ExchangeReceiverApp {
	private static final String EXCHANGE_NAME = "directExchanger";
	
	public static void main(String[] argv) throws Exception {
		
		System.out.println("wright set_topic php");
		System.out.println("wright set_topic java");
		Scanner scanner = new Scanner(System.in);
		String subscription = scanner.nextLine();
		if (subscription.startsWith("set_topic")) subscription = subscription.substring(10);
		
		init(subscription);

	}
	
	private static void init(String subscription) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		
		String queueName = channel.queueDeclare().getQueue();
		
		channel.queueBind(queueName, EXCHANGE_NAME, subscription);
		
		System.out.println(" [*] Waiting for messages");
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received '" + message + "'");
		};
		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
		});
	}
}
