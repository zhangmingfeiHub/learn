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
	public void receiveMsg1(String msg) {
		System.err.println("mqConsumer 【queue-hello】 receive msg: " + msg);
		int a = 1/0;
	}

	@RabbitListener(queues = "queue-valid")
	public void receiveMsg2(String msg) {
		System.err.println("mqConsumer 【queue-valid】 receive msg: " + msg);
		int a = 1/0;
	}

	@RabbitListener(queues = "queue-valid-2")
	public void receiveMsg3(String msg) {
		System.err.println("mqConsumer 【queue-valid-2】 receive msg: " + msg);
		int a = 1/0;
	}
	
}
