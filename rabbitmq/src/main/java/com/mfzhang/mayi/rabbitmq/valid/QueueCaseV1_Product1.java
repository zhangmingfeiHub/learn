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
 * 情况1：一个队列三个消费者订阅监听<br/>
 * 当消息到达队列时，消息会被循环的推送给三个消费者<br/>
 * 
 * 情况2：一个队列三个消费者订阅监听，其中有一个消费者执行慢会延迟<br/>
 * 当消息到达队列时，消息还是会被循环推送给三个消费者，执行慢的消费者也是会获得同样多的消息来消费<br/>
 * 也就是说三个消费者获得消费消息的机会还是一样的，只是执行慢的消费者在慢慢的执行，而其他消费者早已执行完<br/>
 * 
 * 情况3：channel.basicReject(envelope.getDeliveryTag(), true) 拒绝消费这条消息<br/>
 * 如果第二个参数requeue设为true，表示该消费者拒绝消费这条消息，rabbitMQ会把这条消息推送给其他消费者消费<br/>
 * 如果第二个参数requeue设为false，表示该消费者拒绝消费这条消息，rabbitMQ会把这条消息从队列中删除（这条消息其实会进入死信队列中）<br/>
 * 
 * @author mingfei.z
 */
public class QueueCaseV1_Product1 {

	private static final String QUEUE_NAME = "queue_valid_case_v1";
	private static final String EXCHANGE_NAME = "exchange_valid_case_v1";
	private static final String BINGDING_KEY = "binding_key_valid_case_v1";
	private static final Logger logger = LoggerFactory.getLogger(QueueCaseV1_Product1.class);
	
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
				logger.info("QueueCaseV1_Product1发送消息：{}", msg);
			}
			
			channel.close();
			conn.close();
		} catch (Exception e) {
			logger.error("QueueCaseV1_Product1生产者发送消息出错了", e);
		}
	}
	
}
