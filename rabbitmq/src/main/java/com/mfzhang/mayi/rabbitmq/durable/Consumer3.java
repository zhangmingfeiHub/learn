package com.mfzhang.mayi.rabbitmq.durable;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Consumer3 {

	private static final String EXCHANGE_NAME = "test_exchange_durable";
	private static final String EXCHANGE_NAME_NO = "test_exchange_durable_no";
	private static final String ROUTING_KEY = "routing_key";
	private static final String QUEUE_NAME = "test_queue_durable";
	private static final String QUEUE_NAME_NO = "test_queue_durable_no";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection conn = ConnectionUtils.getConn();
		Channel channel = conn.createChannel();
		Channel channel2 = conn.createChannel();
		
//		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true);
		channel.exchangeDeclare(EXCHANGE_NAME_NO, BuiltinExchangeType.DIRECT, false);
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//		channel.queueDeclare(QUEUE_NAME_NO, false, false, false, null);
//		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
//		channel.queueBind(QUEUE_NAME_NO, EXCHANGE_NAME, ROUTING_KEY);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME_NO, ROUTING_KEY);
		
		channel2.exchangeDeclare(EXCHANGE_NAME_NO, BuiltinExchangeType.DIRECT, false);
		channel2.queueDeclare(QUEUE_NAME, true, false, false, null);
		channel2.queueBind(QUEUE_NAME, EXCHANGE_NAME_NO, ROUTING_KEY);
		
		channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
//		channel.basicConsume(QUEUE_NAME_NO, false, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body);
				System.err.println(" [x-3-1] recieve msg: " + msg);
				
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		});

		channel2.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
//		channel.basicConsume(QUEUE_NAME_NO, false, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body);
				System.err.println(" [x-3-2] recieve msg: " + msg);
				
				channel2.basicAck(envelope.getDeliveryTag(), false);
			}
		});
		
	}

}
