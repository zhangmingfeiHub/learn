package com.mfzhang.mayi.rabbitmq.ps;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 发布订阅模式：fanout
 * 
 * @author mingfei.z
 */
public class ProducerPS {

	private static final String EXCHANGE_NAME = "test_exchange_fanout";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection conn = ConnectionUtils.getConn();
		Channel channel = conn.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
		
		for (int i=1; i<=50; i++) {
			String message = "hello exchange fanout 2: " + i;
			
			// 消息发到exchange，exchange会分发消息给绑定它的队列，生产者发送时无需指定routingKey
			channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
			
			System.err.println("send message: " + message);
			
			Thread.sleep(200);
		}
		
		channel.close();
		conn.close();
	}

}
