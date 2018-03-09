package com.mfzhang.mayi.rabbitmq.rpc;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class ConsumerRpc {

	private static final String QUEUE_NAME = "test_queue_rpc";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		System.err.println("consumer waiting for recieving message.");
		
		channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				
				try {
					String message = new String(body, "UTF-8");
					System.err.println("consumer recieve message: " + message);
					
				} finally {
					String correlationId = properties.getCorrelationId();
					String replyQueue = properties.getReplyTo();
					BasicProperties props = new BasicProperties()
							.builder()
							.correlationId(correlationId)
							.build();
					
					String repMsg = "rep: hello rpc ----";
					channel.basicPublish("", replyQueue, props, repMsg.getBytes());
					System.err.println("consumer repsonse message: " + repMsg);
					
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		});
	}

}
