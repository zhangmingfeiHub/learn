package com.mfzhang.mayi.rabbitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.common.constant.CommonConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer3 {

	public static void main(String[] args) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setVirtualHost("/testVH");
		
		try {
			Connection conn = factory.newConnection();
			Channel channel = conn.createChannel();
			channel.queueDeclare(CommonConstants.Rabbit.QUEUE_NAME_test_pro_con, false, false, false, null);
			
			String message = "hello Rabbit，消息传递";
			System.err.println("producer send message : " + message);
			channel.basicPublish("", CommonConstants.Rabbit.QUEUE_NAME_test_pro_con, null, message.getBytes("UTF-8"));
			System.err.println("producer send message success.");
			
			channel.close();
			conn.close();
		} catch (IOException | TimeoutException e) {
			System.err.println("send exception...: " + e);
			e.printStackTrace();
		}
	}
	
}
