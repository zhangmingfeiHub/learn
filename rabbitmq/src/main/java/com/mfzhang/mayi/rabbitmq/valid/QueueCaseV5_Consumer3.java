/**
 * 
 * @author mingfei.z 2018年6月14日 上午9:24:10
 */
package com.mfzhang.mayi.rabbitmq.valid;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 验证<br/>
 * 
 * @author mingfei.z
 */
public class QueueCaseV5_Consumer3 {

	public static final Logger logger = LoggerFactory.getLogger(QueueCaseV5_Consumer3.class);

	public static final String EXCANGE_NAME = "exchange_valid_case_v5";
	public static final String QUEUE_NAME = "queue_valid_case_v5_3";
	public static final String BINDING_KEY = "#";
	
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionUtils.getConn();
			
			Channel channel = conn.createChannel();
			
			channel.exchangeDeclare(EXCANGE_NAME, BuiltinExchangeType.TOPIC);
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			channel.queueBind(QUEUE_NAME, EXCANGE_NAME, BINDING_KEY); 
			
			channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					String msg = new String(body);
					logger.info("QueueCaseV5_Consumer3 接收消息={}", msg);
					
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			});
		} catch (Exception e) {
			logger.error("QueueCaseV5_Consumer3 消费消息异常", e);
		}
	}
	
}
