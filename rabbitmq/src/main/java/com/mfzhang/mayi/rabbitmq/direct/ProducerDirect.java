package com.mfzhang.mayi.rabbitmq.direct;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * direct exchange<br/>
 * 路由模式，routing
 * 
 * @author mingfei.z
 */
public class ProducerDirect {

	private static final String EXCHANGE_NAME = "test_exchange_direct";
	private static final String ROUTING_KEY = "test_queue_exchange_direct_routing_key2";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection conn = ConnectionUtils.getConn();
		Channel channel = conn.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		
		for (int i = 0; i < 50; i++) {
			String message = "hello direct exchange: " + (i+1);
			channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
			Thread.sleep(100);
			System.err.println("send message: " + message);
		}
		
		channel.close();
		conn.close();
	}

}
