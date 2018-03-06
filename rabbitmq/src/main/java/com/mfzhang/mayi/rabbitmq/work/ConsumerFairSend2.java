package com.mfzhang.mayi.rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 公平分发
 * 
 * @author mingfei.z
 */
public class ConsumerFairSend2 {

	private static final String QUEUE_NAME = "test_queue_work_fair";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection conn = ConnectionUtils.getConn();
		Channel channel = conn.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		channel.basicQos(1); // 每次分发一个，待处理完回执后再分发
		
		boolean autoAck = false;
		channel.basicConsume(QUEUE_NAME, autoAck, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				
				try {
					Thread.sleep(1000*2);
					System.err.println("[2] recieve message: " + message);
				} catch (Exception e) {
					System.err.println("[2] recieve message exception: " + e);
				} finally {
					// 手动回执确认
					channel.basicAck(envelope.getDeliveryTag(), true);
					System.err.println("[2] recieve message done.");
				}
			}
		});
	}

}
