/**
 * 
 * @author mingfei.z 2018年3月10日 下午10:21:11
 */
package com.mfzhang.mayi.rabbitmq.tx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 生产者发送消息到RabbitMq，确认是否发送成功，方式二：confirm，最大优点是异步<br/>
 * 
 * 原理：生产者将信道channel设置confirm模式后，所有在该channel上发布的消息都会指派一个唯一的id，从1开始<br/>
 * 一旦消息被投递到相应的队列后，rabbitMq会发送一个确认消息给生产者，使生产者知道消息已经成功发送到队列了，<br/>
 * 如果消息是可持久化的，则rabbitMq会把消息写入磁盘后再发送确认消息给生产者<br/>
 * 
 * 三种编程方式：
 * 1、生产者发送单条消息，之后调用waitForConfirms<br/>
 * 2、生成者发送批量消息，之后调用waitForConfirms<br/>
 * 3、生产者异步发送，提供一个回调方法<br/>
 * 
 * @author mingfei.z
 */
public class ProducerConfirm {

	private static final String QUEUE_NAME = "test_queue_tx_confirm";
	
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
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String message = "hello queue confirm.";
		String messageBatch = "hello queue confirm batch.";
		
		// 信道设置成confirm模式
		// 注意：如果一个队列是事务机制的，再改成confirm模式会报异常
		channel.confirmSelect();
		
		/*
		 * 单条
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		*/
		
		// 批量发送消息
		for (int i=0; i<10; i++) {
			channel.basicPublish("", QUEUE_NAME, null, messageBatch.getBytes());
		}
		
		System.err.println(" [x] send message: " + message);
		
		// 确认是否发送成功
		if (channel.waitForConfirms()) { // 发送成功
			System.err.println(" [x] send message success");
		} else { // 发送失败
			System.err.println(" [x] send message fail");
		}
		
		channel.close();
		connection.close();
	}

}
