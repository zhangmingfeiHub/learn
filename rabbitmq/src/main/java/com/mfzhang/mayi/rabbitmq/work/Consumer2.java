package com.mfzhang.mayi.rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * work queue 工作队列<br/>
 * 轮询分发，链接到该队列的所有消费者，循环分发
 * 
 * @author mingfei.z
 */
public class Consumer2 {

	private static final String QUEUE_NAME = "test_queue_work";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {

				try {
					String message = new String(body, "UTF-8");
					System.err.println("[2] recieve msg: " + message);
					
					Thread.sleep(1000 * 3);
				} catch (InterruptedException e) {
					
				} finally {
					System.err.println("[2] recieve msg done.");
				}
			}
		};
		
		// 监控队列
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}

}
