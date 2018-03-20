package com.mfzhang.mayi.rabbitmq.durable;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * 持久化验证
 * 
 * @author mingfei.z
 */
public class Producer {

	private static final String EXCHANGE_NAME = "test_exchange_durable";
	private static final String EXCHANGE_NAME_NO = "test_exchange_durable_no";
	private static final String ROUTING_KEY = "routing_key";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection conn = ConnectionUtils.getConn();
		Channel channel = conn.createChannel();
		
//		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true);
		channel.exchangeDeclare(EXCHANGE_NAME_NO, BuiltinExchangeType.DIRECT, false);
		
		for (int i=0; i<20; i++) {

			String msg = "hello durable: " + (i+1);
			
			if (i < 0) {
				
//				channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());
				channel.basicPublish(EXCHANGE_NAME_NO, ROUTING_KEY, null, msg.getBytes());
			} else {
//				BasicProperties properties = new AMQP.BasicProperties().builder().deliveryMode(2).build();

				// 设置模式为2，表示消息持久化
//				channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
				channel.basicPublish(EXCHANGE_NAME_NO, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
			}
			
			System.err.println(" [x] send msg: " + msg);
		}
		
		channel.close();
		conn.close();
		
	}

}
