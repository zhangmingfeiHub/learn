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
 * Fanout类型交换器总结：
 * 1、队列绑fanout类型交换器不需要指定BINDING_KEY，指定或不指定没意义；
 * 2、生产者发送消息到fanout类型交换器，不需要指定路由键，路由键对fanout类型交换器来说是没有意义的；
 * 3、fanout类型交换器是直接把消息投递到绑在其上面的队列的；
 * 
 * @author mingfei.z
 */
public class QueueCaseV4_Product1_Fanout {

	public static final Logger logger = LoggerFactory.getLogger(QueueCaseV4_Product1_Fanout.class);
	
	public static final String EXCANGE_NAME = "exchange_valid_case_v4";
	
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionUtils.getConn();
			
			Channel channel = conn.createChannel();
			
			channel.exchangeDeclare(EXCANGE_NAME, BuiltinExchangeType.FANOUT);
			
			String msg = "";
			for (int i=1; i<=20; i++) {
				msg = "send msg" + i;
				channel.basicPublish(EXCANGE_NAME, "", null, msg.getBytes());
				
				logger.info("QueueCaseV4_Product1_Fanout 生产发送消息={}", msg);
			}
			
			channel.close();
			conn.close();
		} catch (Exception e) {
			logger.error("QueueCaseV4_Product1_Fanout 生产消息异常", e);
		}
	}
	
}
