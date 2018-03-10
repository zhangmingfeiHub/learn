/**
 * 
 * @author mingfei.z 2018年3月10日 下午10:39:32
 */
package com.mfzhang.mayi.rabbitmq.tx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.common.constant.CommonConstants;
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
public class ConsumerConfirm {

	private static final String QUEUE_NAME = "test_queue_tx_confirm";
	
	/**
	 * 
	 * @author mingfei.z
	 * @param args
	 * @throws TimeoutException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		System.err.println(" [x] consumer wait for message. ");
		
		channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
			/**
			 * 
			 * @author mingfei.z
			 * @param consumerTag
			 * @param envelope
			 * @param properties
			 * @param body
			 * @throws IOException
			 */
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, CommonConstants.CodeFormat.FORMAT_UTF8);
				System.err.println(" [x] consumer recieve message: " + message);
			}
		});
	}

}
