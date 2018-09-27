/**
 * 
 * @author mingfei.z 2018年9月28日 上午12:09:55
 */
package com.mfzhang.mayi.springboot.rabbitmq.consumer.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
@Component
public class MqConsumer {

	@RabbitListener(queues = "queue-hello")
	public void receiveMsg(String msg) {
		System.out.println("mqConsumer receive msg: " + msg);
	}
	
}
