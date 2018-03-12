package com.mfzhang.mayi.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息生产者
 * @author mingfei.z
 *
 */
public class Producer22 {

	private static final String QUEUE_NAME = "rabbit.task"; // 队列名称
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory(); // 连接工厂
		
		connectionFactory.setHost("localhost");
		
		try {
			Connection connection = connectionFactory.newConnection(); // 创建连接
			
			Channel channel = connection.createChannel(); // 创建通道
			channel.queueDeclare(QUEUE_NAME, false, false, false, null); // 声明队列
			
			for (int i=1; i<=10; i++) {
				String message = "消息队列hello (" + i + ")"; // 发送的消息
				channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
				System.err.println("producer send message: " + message);
			}
			
			channel.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
