package com.mfzhang.mayi.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {

	private static final String QUEUE_NAME = "test-queue-simple";
	private static final String QUEUE_NAME_2 = "test-queue-simple2";
	private static final String QUEUE_NAME_3 = "test-queue-simple3";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueDeclare(QUEUE_NAME_2, false, false, false, null);
		channel.queueDeclare(QUEUE_NAME_3, false, false, false, null);
		
		for (int i=0; i<1; i++) {
			
			String message = "hello queue simple." + (i+1);
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			channel.basicPublish("", QUEUE_NAME_2, null, message.getBytes());
			channel.basicPublish("", QUEUE_NAME_3, null, message.getBytes());
			
			System.err.println("send message: " + message);
		}
		
		channel.close();
		connection.close();
	}

}
