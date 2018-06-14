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
 * topic类型交换器：
 * 1、队列、交换器绑定关系，可用通配符（* \/ #），
 * * —— 表示一个块的所有匹配，如 A.*
 * # —— 表示所有匹配，如#
 * 2、生产者发送消息到topic类型交换器，要指定路由键，当路由键匹配队列与交换器绑定关系时，
 * 则交换器会把消息投递到对应队列中
 * 
 * @author mingfei.z
 */
public class QueueCaseV5_Product1_Topic {

	public static final Logger logger = LoggerFactory.getLogger(QueueCaseV5_Product1_Topic.class);
	
	public static final String EXCANGE_NAME = "exchange_valid_case_v5";
	public static final String ROUTING_KEY = "binding_key_valid_case_v5.A";
	
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionUtils.getConn();
			
			Channel channel = conn.createChannel();
			
			channel.exchangeDeclare(EXCANGE_NAME, BuiltinExchangeType.TOPIC);
			
			String msg = "";
			for (int i=1; i<=20; i++) {
				msg = "send msg" + i;
				channel.basicPublish(EXCANGE_NAME, ROUTING_KEY, null, msg.getBytes());
				
				logger.info("QueueCaseV5_Product1_Topic 生产发送消息={}", msg);
			}
			
			channel.close();
			conn.close();
		} catch (Exception e) {
			logger.error("QueueCaseV5_Product1_Topic 生产消息异常", e);
		}
	}
	
}
