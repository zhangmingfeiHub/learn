/**
 * 
 * @author mingfei.z 2018年6月14日 上午9:24:10
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
 * 验证<br/>
 * 
 * 情况1：<br/>
 * direct类型的交换器，是否可以被多个队列使用同一个路由键绑定<br/>
 * 
 * @author mingfei.z
 */
public class QueueCaseV3_Consumer2 {

	public static final Logger logger = LoggerFactory.getLogger(QueueCaseV3_Consumer2.class);

	public static final String QUEUE_NAME_2 = "queue_valid_case_v3_2";
	
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionUtils.getConn();
			
			Channel channel = conn.createChannel();
			
			channel.queueDeclare(QUEUE_NAME_2, false, false, false, null);
			
			channel.basicConsume(QUEUE_NAME_2, false, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					String msg = new String(body);
					logger.info("QueueCaseV3_Consumer2 接收消息={}", msg);
					
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			});
		} catch (Exception e) {
			logger.error("QueueCaseV3_Consumer2 消费消息异常", e);
		}
	}
}
