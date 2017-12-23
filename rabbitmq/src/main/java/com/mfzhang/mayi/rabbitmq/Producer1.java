/**
 * 
 * @author mingfei.z 2017年12月18日 下午9:33:14
 */
package com.mfzhang.mayi.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息-生产者
 * @author mingfei.z
 */
public class Producer1 {

	public static void main(String[] args) {
		try {
			String queue_name = "hello_world";
			
			ConnectionFactory factory = new ConnectionFactory();
			
			Connection connection = factory.newConnection();
			
			Channel channel = connection.createChannel();
			
			channel.queueDeclare(queue_name, false, false, false, null);
			
			String message = "中国客户1";
			
			channel.basicPublish("", queue_name, null, message.getBytes("UTF-8"));
			System.err.println("成功发布");
			
			channel.close();
			connection.close();
		} catch (Exception e) {
			System.err.println("e: " + e);
		}
	}
	
}
