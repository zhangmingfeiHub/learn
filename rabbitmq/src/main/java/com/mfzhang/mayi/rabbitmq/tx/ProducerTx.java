/**
 * 
 * @author mingfei.z 2018年3月10日 下午9:52:16
 */
package com.mfzhang.mayi.rabbitmq.tx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 生产者消息确认机制（事务+confirm）<br/>
 * 确认消息是否到达rabbitMq<br/>
 * 方式一：AMQP协议实现了事务机制（会降低rabbitMq的吞吐量）<br/>
 * 方式二：confirm模式
 * 
 * @author mingfei.z
 */
public class ProducerTx {

	private static final String QUEUE_NAME = "test_queue_tx";
	
	/**
	 * 
	 * @author mingfei.z
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = null;
		Channel channel = null;
		
		try {
			connection = ConnectionUtils.getConn();
			channel = connection.createChannel();
			
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			String message = "hello queue tx.";
			
			// 开始事务机制
			channel.txSelect();
			
			// 发送消息到rabbitmq，其中有可能会发生异常
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.err.println(" [x] send msg: " + message);
			
			// 测试发生异常，事务rollback，消息没有发送成功，自然消费者也接收不到
			int i = 1/0;
			
			// 事务提交后才会真正发送到rabbitMq
			channel.txCommit();
		} catch (Exception e) {
			try {
				channel.txRollback();
				System.err.println(" [x] send msg rollback. ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				channel.close();
				connection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
