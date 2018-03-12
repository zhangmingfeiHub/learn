package com.mfzhang.mayi.rabbitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.common.constant.CommonConstants;
import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class RecieveForDirectEx {

	private static final String QUEUE_NAME = "test_exchange_direct_queue1";
	private static final String EXCHANGE_NAME = "test_exchange_direct1";
	private static final String ROUTING_KEY_1 = "test_exchange_direct_routing_key_1";
	private static final String ROUTING_KEY_2 = "test_exchange_direct_routing_key_2";
	private static final String ROUTING_KEY_3 = "test_exchange_direct_routing_key_3";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY_1);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY_2);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY_3);
		
		System.err.println(" [x] waiting for message.");
		
		channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, CommonConstants.CodeFormat.FORMAT_UTF8);
				System.err.println(" [x] recieve message: " + message);
			}
		});
		
	}

}
