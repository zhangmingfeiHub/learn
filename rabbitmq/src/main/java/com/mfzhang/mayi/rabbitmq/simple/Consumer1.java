package com.mfzhang.mayi.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 简单消息队列
 * 
 * @author mingfei.z
 */
public class Consumer1 {

	private static final String QUEUE_NAME = "test-queue-simple";
	private static final String QUEUE_NAME_2 = "test-queue-simple2";
	private static final String QUEUE_NAME_3 = "test-queue-simple3";
	private static final String QUEUE_NAME_4 = "test-queue-simple4";
	private static final String QUEUE_NAME_5 = "test-queue-simple5";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				
				try {
					
					String message = new String(body, "UTF-8");
					System.err.println("consumer1 recieve message: " + message);
					
//					1. 测试消费者发生异常，消息队列会不停的尝试推送消息给消费者，
//					主要是basicNack()方法的requeue=true参数起作用，为false时就当处理失败了，消息也从队列中删除了
//					int i = 1/0;
					
//					2.测试消息到达消费者后，在消息确认前断开与队列的连接
//					此时消息还在队列中未被删除，队列会把消息推送给其他的消费者，如果只有该一个消费者的话，则等到该消费者与队列重新连接，再推送给它
//					Thread.sleep(1000 * 10);
					
//					3.测试消费者发生异常，不返回确认消息给队列，
//					则队列不会再往该消费者推送消息了，队列认为在上一条消息没有确认之前，该消费者还没有准备好接收下一条消息
//					也不调basicNack()方法
//					String str = null;
//					boolean flag = str.equals("abc");
					
					channel.basicAck(envelope.getDeliveryTag(), false);
				} catch (Exception e) {
					System.err.println("consumer1 recieve msg exception: " + e);
					
					// 拒绝消息
					// deliveryTag:该消息的index、
					// multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息
					// requeue：被拒绝的是否重新入队列
//					channel.basicNack(envelope.getDeliveryTag(), false, false);
				}
			}
		};
		
		channel.basicConsume(QUEUE_NAME_5, false, consumer);
	}

}
