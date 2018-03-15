package com.mfzhang.mayi.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {

	private static final String QUEUE_NAME = "test-queue-simple";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		for (int i=0; i<100; i++) {
			
			String message = "hello queue simple." + (i+1);
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			
			System.err.println("send message: " + message);
		}
		
		channel.close();
		connection.close();
	}

}
