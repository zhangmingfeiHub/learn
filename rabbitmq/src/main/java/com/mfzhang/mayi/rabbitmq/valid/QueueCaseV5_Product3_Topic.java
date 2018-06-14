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
 * @author mingfei.z
 */
public class QueueCaseV5_Product3_Topic {

	public static final Logger logger = LoggerFactory.getLogger(QueueCaseV5_Product3_Topic.class);
	
	public static final String EXCANGE_NAME = "exchange_valid_case_v5";
	public static final String ROUTING_KEY = "routing_key_valid_case_v5.C";
	
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionUtils.getConn();
			
			Channel channel = conn.createChannel();
			
			channel.exchangeDeclare(EXCANGE_NAME, BuiltinExchangeType.TOPIC);
			
			String msg = "";
			for (int i=1; i<=20; i++) {
				msg = "send msg" + i;
				channel.basicPublish(EXCANGE_NAME, ROUTING_KEY, null, msg.getBytes());
				
				logger.info("QueueCaseV5_Product3_Topic 生产发送消息={}", msg);
			}
			
			channel.close();
			conn.close();
		} catch (Exception e) {
			logger.error("QueueCaseV5_Product3_Topic 生产消息异常", e);
		}
	}
	
}
