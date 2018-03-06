package com.mfzhang.mayi.rabbitmq.ps;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * exchange 类型：fanout<br/>
 * 发布订阅模式
 * 
 * @author mingfei.z
 */
public class ConsumerPS2 {

	private static final String EXCHANGE_NAME = "test_exchange_fanout";
	private static final String ROUTING_KEY = "test_exchange_fanout_routing_key2";
	private static final String QUEUE_NAME = "test_queue_exchange_fanout2";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection conn = ConnectionUtils.getConn();
		Channel channel = conn.createChannel();
		
		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 队列绑定到exchange
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
		
		channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				
				try {
					Thread.sleep(1000*1);
					System.err.println("[2] recieve message: " + message);
				} catch (Exception e) {
					System.err.println("[2] recieve message exception.");
				} finally {
					System.err.println("[2] recieve message done.");
				}
			}
		});
	}

}
