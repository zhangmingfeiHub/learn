/**
 * 
 * @author mingfei.z 2017年12月18日 下午9:33:50
 */
package com.mfzhang.mayi.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 
 * @author mingfei.z
 */
public class Consumer12 {

	public static void main(String[] args) {
		try {
			String queue_name = "hello_world";
			
			ConnectionFactory connectionFactory = new ConnectionFactory();
			
			Connection connection = connectionFactory.newConnection();
			
			Channel channel = connection.createChannel();
			
			channel.queueDeclare(queue_name, false, false, false, null);
			
			Consumer consumer = new DefaultConsumer(channel) {
				/**
				 * 
				 * @author mingfei.z
				 * @param consumerTag
				 * @param envelope
				 * @param properties
				 * @param body
				 * @throws IOException
				 */
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.err.println("msg: " + message);
				}
			};
			
			channel.basicConsume(queue_name, true, consumer);
			System.err.println("成功消费2");
			
		} catch (Exception e) {
			System.err.println("e: " + e);
		}
	}
	
}
