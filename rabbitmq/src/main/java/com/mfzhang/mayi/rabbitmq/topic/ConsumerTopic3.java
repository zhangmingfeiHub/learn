/**
 * 
 * @author mingfei.z 2018年3月6日 下午11:02:34
 */
package com.mfzhang.mayi.rabbitmq.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 
 * @author mingfei.z
 */
public class ConsumerTopic3 {

	private static final String QUEUE_NAME = "test_queue_exchange_topic3";
	private static final String EXCHANGE_NAME = "test_exchange_topic";
	private static final String ROUTING_KEY = "type.topic";
	
	/**
	 * 
	 * @author mingfei.z
	 * @param args
	 * @throws TimeoutException
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 队列绑定到exchange
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
		
		boolean autoAck = false;
		channel.basicConsume(QUEUE_NAME, autoAck, new DefaultConsumer(channel) {
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
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				
				try {
					Thread.sleep(1000*3);
					System.err.println("[3] recieve message: " + message);
				} catch (InterruptedException e) {
					System.err.println("[3] recieve message exception: " + e);
				} finally {
					System.err.println("[3] recieve done.");
					channel.basicAck(envelope.getDeliveryTag(), true);
				}
			}
		});
	}

}
