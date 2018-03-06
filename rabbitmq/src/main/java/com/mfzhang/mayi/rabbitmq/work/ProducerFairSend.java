package com.mfzhang.mayi.rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 公平分发
 * 
 * @author mingfei.z
 */
public class ProducerFairSend {

	private static final String QUEUE_NAME = "test_queue_work_fair";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection conn = ConnectionUtils.getConn();
		Channel channel = conn.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		channel.basicQos(1); // 每次只分发一个
		
		for (int i=0; i<50; i++) {
			String message = "hello wq fair: " + (i+1);
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			
			System.err.println("send message: " + message);
			Thread.sleep(100);
		}
		
		channel.close();
		conn.close();
		
	}

}
