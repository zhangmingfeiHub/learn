/**
 * 
 * @author mingfei.z 2018年3月16日 下午8:58:40
 */
package com.mfzhang.mayi.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.common.constant.CommonConstants;
import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 
 * @author mingfei.z
 */
public class SendV1 {
	
	private static final String EXCHANGE_NAME = "test-exchange-direct-attr";
	private static final String ROUTING_KEY = "routing-key";

	/**
	 * 
	 * @author mingfei.z
	 * @param args
	 * @throws TimeoutException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection conn = ConnectionUtils.getConn();
		Channel channel = conn.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		
		String msg = " [x] send msg.";
		channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes(CommonConstants.CodeFormat.FORMAT_UTF8));
		System.err.println(msg);
		
		channel.close();
		conn.close();
	}

}
