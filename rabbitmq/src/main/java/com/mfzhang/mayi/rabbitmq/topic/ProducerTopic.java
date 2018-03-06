/**
 * 
 * @author mingfei.z 2018年3月6日 下午10:43:05
 */
package com.mfzhang.mayi.rabbitmq.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 主题模式
 * @author mingfei.z
 */
public class ProducerTopic {
	
	private static final String EXCHANGE_NAME = "test_exchange_topic";
	private static final String ROUTING_KEY = "exchange.*";

	/**
	 * 
	 * @author mingfei.z
	 * @param args
	 * @throws TimeoutException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		// 声明exchange，topic类型
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
		
		for (int i=0; i<20; i++) {
			String message = "hello exchange topic: " + (i + 1);
			Thread.sleep(100);
			
			// 消息发送到exchange
			channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
			System.err.println("send message: " + message);
		}
		
		channel.close();
		connection.close();
		
	}

}
