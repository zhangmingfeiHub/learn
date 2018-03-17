/**
 * 
 * @author mingfei.z 2018年3月16日 下午9:03:46
 */
package com.mfzhang.mayi.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.common.constant.CommonConstants;
import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 
 * @author mingfei.z
 */
public class Recv14 {

	private static final String EXCHANGE_NAME = "test-exchange-direct-attr";
	private static final String QUEUE_NAME_1 = "test-queue-attr-1";
	private static final String QUEUE_NAME_2 = "test-queue-attr-2";
	private static final String QUEUE_NAME_3 = "test-queue-attr-3";
	private static final String QUEUE_NAME_4 = "test-queue-attr-4";
	private static final String ROUTING_KEY = "routing-key";

	/**
	 * 
	 * @author mingfei.z
	 * @param args
	 * @throws TimeoutException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection conn = ConnectionUtils.getConn();
		Channel channel = conn.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		
		/**
		 * queue: 队列名
		 * durable：是否持久化
		 * exclusive：是否排他，true表示私有，只有当前消费者才能消费该队列消息
		 * autoDelete：当没有消费者订阅/监听时，是否自动删除
		 */
//		channel.queueDeclare(QUEUE_NAME_1, false, true, false, null);
//		channel.queueDeclare(QUEUE_NAME_2, false, false, true, null);
//		channel.queueDeclare(QUEUE_NAME_3, false, false, false, null);
		channel.queueDeclare(QUEUE_NAME_4, true, false, false, null);
//		channel.queueDeclarePassive(QUEUE_NAME_3);
		
		channel.queueBind(QUEUE_NAME_3, EXCHANGE_NAME, ROUTING_KEY);
		
		channel.basicConsume(QUEUE_NAME_3, false, new DefaultConsumer(channel) {
			/**
			 * 
			 * @author mingfei.z
			 * @param consumerTag
			 * @param envelope
			 * @param properties
			 * @param body
			 * @throws IOException
			 */
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				try {
					String msg = new String(body, CommonConstants.CodeFormat.FORMAT_UTF8);
					System.err.println(" [x-3] recieve msg: " + msg + ", deliveryTag: " + envelope.getDeliveryTag());
					
					channel.basicAck(envelope.getDeliveryTag(), false);
				} catch (Exception e) {
					System.err.println(" [x-3] recieve msg exception: " + e + ", deliveryTag: " + envelope.getDeliveryTag());
					channel.basicNack(envelope.getDeliveryTag(), false, false);
				}
				
			}
		});
	}

}
