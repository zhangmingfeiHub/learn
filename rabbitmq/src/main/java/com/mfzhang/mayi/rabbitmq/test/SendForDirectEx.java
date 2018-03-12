package com.mfzhang.mayi.rabbitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 测试direct类型exchange，一个交换器exchange绑一个队列queue，多个绑定key
 * 
 * @author mingfei.z
 */
public class SendForDirectEx {

	private static final String EXCHANGE_NAME = "test_exchange_direct1";
	private static final String ROUTING_KEY_1 = "test_exchange_direct_routing_key_3";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		
		String message = "hello direct exchange more routing key.";
		channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY_1, null, message.getBytes());
		System.err.println(" [x] send message: " + message);
		
		channel.close();
		connection.close();
	}

}
