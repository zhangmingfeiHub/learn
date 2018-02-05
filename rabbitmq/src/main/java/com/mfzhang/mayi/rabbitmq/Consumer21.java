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
 * 消息生产者
 * @author mingfei.z
 *
 */
public class Consumer21 {

	private static final String QUEUE_NAME = "rabbit.test"; // 队列名称
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory(); // 连接工厂
		
		connectionFactory.setHost("localhost");
		
		try {
			Connection connection = connectionFactory.newConnection(); // 创建连接
			
			Channel channel = connection.createChannel(); // 创建通道
			channel.queueDeclare(QUEUE_NAME, false, false, false, null); // 声明关注的队列
			System.err.println("consumer's waiting for recieving message.");
			
			// 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body);
					System.err.println("consumer recieve message: " + message);
				}			
			};
			
			channel.basicConsume(QUEUE_NAME, true, consumer); // 自动回复队列应答 -- RabbitMQ中的消息确认机制
			
			/*channel.close();
			connection.close();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
