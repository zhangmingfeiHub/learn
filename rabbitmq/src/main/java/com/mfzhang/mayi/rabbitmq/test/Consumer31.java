package com.mfzhang.mayi.rabbitmq.test;

import java.io.IOException;

import com.mfzhang.mayi.common.constant.CommonConstants;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer31 {

	public static void main(String[] args) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setVirtualHost("/testVH");
		
		try {
			Connection conn = factory.newConnection();
			Channel channel = conn.createChannel();
			channel.queueDeclare(CommonConstants.Rabbit.QUEUE_NAME_test_pro_con, false, false, false, null);
			System.err.println("consumer31 waiting for recieve...");
			
			channel.basicQos(1); // 每次读取一条信息
			
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.err.println("consumer31 recieve message: " + message);
				}
			};
			
			channel.basicConsume(CommonConstants.Rabbit.QUEUE_NAME_test_pro_con, true, consumer); // 订阅队列
		} catch (Exception e) {
			System.err.println("recieve message exception: " + e);
		}
	}
	
}
