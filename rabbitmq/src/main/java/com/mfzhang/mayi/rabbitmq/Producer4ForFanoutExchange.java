package com.mfzhang.mayi.rabbitmq;

import java.util.Date;

import com.mfzhang.mayi.common.constant.CommonConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer4ForFanoutExchange {

	public static void main(String[] args) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("mingfei");
		factory.setPassword("mingfei");
		
		try {
			Connection conn = factory.newConnection();
			Channel channel = conn.createChannel();
			channel.exchangeDeclare(CommonConstants.Rabbit.EXCHANGE_NAME_ex_log, "a");
			
			String message = "send hello" + new Date().toString();
			channel.basicPublish(CommonConstants.Rabbit.EXCHANGE_NAME_ex_log, null, null, message.getBytes());
			
			channel.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("send exception: " + e);
		}
	}

}
