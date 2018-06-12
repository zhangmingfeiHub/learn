/**
 * 
 * @author mingfei.z 2018年6月11日 上午9:54:02
 */
package com.mfzhang.mayi.rabbitmq.valid;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 
 * @author mingfei.z
 */
public class QueueCaseV1_Consumer2 {

	private static final String QUEUE_NAME = "queue_valid_case_v1";
	private static final Logger logger = LoggerFactory.getLogger(QueueCaseV1_Consumer2.class);
	
	public static void main(String[] args) {
		try {
			// 获取连接
			Connection conn = ConnectionUtils.getConn();
			
			// 创建信道
			Channel channel = conn.createChannel();
			
			// 声明队列
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			// 监听队列，消费消息
			channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					String msg = new String(body);
					logger.info("QueueCaseV1_Consumer2消费消息={}", msg);
					
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			});
			
		} catch (Exception e) {
			logger.error("QueueCaseV1_Consumer2消费消息出错了", e);
		}
	}
	
}
