package com.mfzhang.mayi.rabbitmq.direct;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * direct exchange
 * 
 * @author mingfei.z
 */
public class ConsumerDirect3 {

	private static final String EXCHANGE_NAME = "test_exchange_direct";
	private static final String QUEUE_NAME = "test_queue_exchange_direct3";
	private static final String ROUTING_KEY = "test_queue_exchange_direct_routing_key1";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection conn = ConnectionUtils.getConn();
		Channel channel = conn.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
		
		boolean autoAck = false;
		channel.basicConsume(QUEUE_NAME, autoAck, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				
				try {
					Thread.sleep(1000*1);
					
					System.err.println("[3] recieve message: " + message);
				} catch (InterruptedException e) {
					System.err.println("[3] recieve message exception: " + e);
				} finally {
					channel.basicAck(envelope.getDeliveryTag(), true);
					System.err.println("[3] recieve message done.");
				}
			}
		});
	}

}
