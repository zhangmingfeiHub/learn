/**
 * 
 * @author mingfei.z 2018年6月11日 上午9:31:30
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
public class QueueCaseV2_Consumer1 {

	private static final String QUEUE_NAME_V1 = "queue_valid_case_v1";
	private static final String QUEUE_NAME_V2 = "queue_valid_case_v2";
	private static final Logger logger = LoggerFactory.getLogger(QueueCaseV2_Consumer1.class);
	
	public static void main(String[] args) {
		try {
			// 获取连接
			Connection conn = ConnectionUtils.getConn();
			
			// 创建信道
			Channel channel = conn.createChannel();
			
			// 声明队列
			channel.queueDeclare(QUEUE_NAME_V2, false, false, false, null);
			
			// 监听队列，消费消息
			channel.basicConsume(QUEUE_NAME_V2, false, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					String msg = new String(body);
					logger.info("QueueCaseV2_Consumer1消费消息={}，{}", msg, QUEUE_NAME_V2);
					
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			});
			
			channel.queueDeclare(QUEUE_NAME_V1, false, false, false, null);
			channel.basicConsume(QUEUE_NAME_V1, false, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					String msg = new String(body);
					logger.info("QueueCaseV2_Consumer1消费消息={}，{}", msg, QUEUE_NAME_V1);
					
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			});
		} catch (Exception e) {
			logger.error("QueueCaseV2_Consumer1消费消息出错了", e);
		}
	}
	
}
