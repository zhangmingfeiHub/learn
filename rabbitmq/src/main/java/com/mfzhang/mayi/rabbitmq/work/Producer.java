package com.mfzhang.mayi.rabbitmq.work;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {

	private static final String QUEUE_NAME = "test_queue_work";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		long start = new Date().getTime();
		for (int i=0; i<50; i++) {
			String message = "hello queue work: " + (i+1);
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			
			Thread.sleep(200);
			
			System.err.println("send message: " + message);
		}
		long end = new Date().getTime();
		System.err.println("send message 耗时：" + (end-start));
		
		channel.close();
		connection.close();
	}

}
