/**
 * 
 * @author mingfei.z 2018年6月14日 上午9:24:10
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
 * direct类型的交换器，是否可以被多个队列使用同一个路由键绑定<br/>
 * 1、首先是可以的；<br/>
 * 2、其次是一个交换器ECHANGE1，用同一个路由键BINGDING_KEY绑了两个队列QUEUE1、QUEUE2，<br/>
 * QUEUE1和QUEUE2分别被一个消费者订阅了，当消息由生产者发送到交换器EXCHANGE1时，EXCHANGE1会把消息分别推送给QUEUE1和QUEUE2，<br/>
 * QUEUE1被CONSUMER1消费了队列中的全部消息，QUEUE2被CONSUMER2消费了队列中的全部消息，<br/>
 * 其实QUEUE1和QUEUE2中的消息是一样的<br/>
 * 
 * @author mingfei.z
 */
public class QueueCaseV3_Product1 {

	public static final Logger logger = LoggerFactory.getLogger(QueueCaseV3_Product1.class);
	
	public static final String EXCANGE_NAME = "exchange_valid_case_v3";
	public static final String BINDING_KEY = "binding_key_valid_case_v3";
	public static final String QUEUE_NAME = "queue_valid_case_v3";
	public static final String QUEUE_NAME_2 = "queue_valid_case_v3_2";
	
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionUtils.getConn();
			
			Channel channel = conn.createChannel();
			
			channel.exchangeDeclare(EXCANGE_NAME, BuiltinExchangeType.DIRECT);
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			channel.queueBind(QUEUE_NAME, EXCANGE_NAME, BINDING_KEY);
			channel.queueBind(QUEUE_NAME_2, EXCANGE_NAME, BINDING_KEY);
			
			String msg = "";
			for (int i=1; i<=20; i++) {
				msg = "QueueCaseV3_Product1 send msg" + i;
				channel.basicPublish(EXCANGE_NAME, BINDING_KEY, null, msg.getBytes());
				
				logger.info("QueueCaseV3_Product1 生产发送消息={}", msg);
			}
			
			channel.close();
			conn.close();
		} catch (Exception e) {
			logger.error("QueueCaseV3_Product1 生产消息异常", e);
		}
	}
	
}
