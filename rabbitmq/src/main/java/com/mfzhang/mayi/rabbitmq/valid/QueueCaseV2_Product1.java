/**
 * 
 * @author mingfei.z 2018年6月11日 上午10:26:38
 */
package com.mfzhang.mayi.rabbitmq.valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 验证<br/>
 * 
 * 情况1：<br/>
 * 一个消费者在一个信道上，是否能订阅多个队列<br/>
 * 
 * @author mingfei.z
 */
public class QueueCaseV2_Product1 {

	private static final String QUEUE_NAME = "queue_valid_case_v2";
	private static final String EXCHANGE_NAME = "exchange_valid_case_v2";
	private static final String BINGDING_KEY = "binding_key_valid_case_v2";
	private static final Logger logger = LoggerFactory.getLogger(QueueCaseV2_Product1.class);
	
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionUtils.getConn();
			
			Channel channel = conn.createChannel();
			
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
			channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, BINGDING_KEY);
			
			for (int i=1; i<=100; i++) {
				String msg = "message" + i;
				channel.basicPublish(EXCHANGE_NAME, BINGDING_KEY, null, msg.getBytes());
				logger.info("QueueCaseV2_Product1发送消息：{}", msg);
			}
			
			channel.close();
			conn.close();
		} catch (Exception e) {
			logger.error("QueueCaseV2_Product1生产者发送消息出错了", e);
		}
	}
	
}
