package com.mfzhang.mayi.rabbitmq.rpc;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ProducerRpc {

	private static final String QUEUE_NAME = "test_queue_rpc";
	private static final String REPLY_TO_QUEUE_NAME = "test_queue_rpc_reply_to";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueDeclare(REPLY_TO_QUEUE_NAME, false, false, false, null);
		
		String message = "req: hello rpc";
		String correlationId = UUID.randomUUID().toString();
		BasicProperties props = new BasicProperties()
				.builder()
				.correlationId(correlationId)
				.replyTo(REPLY_TO_QUEUE_NAME)
				.build();
		
		// 发布信息
		channel.basicPublish("", QUEUE_NAME, props, message.getBytes());
		System.err.println("producer send req message: " + message);
		
		System.err.println("producer waiting for recieving rep message.");
		// 监听回复队列
		channel.basicConsume(REPLY_TO_QUEUE_NAME, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String corIdResponse = properties.getCorrelationId();
				
				if (!corIdResponse.equals(correlationId)) {
					System.err.println("producer recieve rep fail, correlationId" + corIdResponse);
					return;
				}
				
				String repMsg = new String(body, "UTF-8");
				System.err.println("producer recieve rep message: " + repMsg);
			}
		});
	}

}
