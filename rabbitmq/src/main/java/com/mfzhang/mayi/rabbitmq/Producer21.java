package com.mfzhang.mayi.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息生产者
 * @author mingfei.z
 *
 */
public class Producer21 {

	private static final String QUEUE_NAME = "rabbit.test"; // 队列名称
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory(); // 连接工厂
		
		connectionFactory.setHost("localhost");
		
		try {
			Connection connection = connectionFactory.newConnection(); // 创建连接
			
			Channel channel = connection.createChannel(); // 创建通道
			
			/**
			 * 声明队列<br/>
			 * queue：队列名称
			 * durable：是否支持持久化，持久化后即使服务重启，队列还在
			 * 
			 */
			channel.queueDeclare(QUEUE_NAME, false, false, false, null); // 声明队列
			
			String message = "消息队列hello"; // 发送的消息
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
			System.err.println("producer send message: " + message);
			
			channel.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
