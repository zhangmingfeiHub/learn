/**
 * 
 * @author mingfei.z 2018年9月27日 下午11:58:36
 */
package com.mfzhang.mayi.springboot.rabbitmq.producer.component;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author mingfei.z
 */
@Component
public class MqProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMsg() {
		rabbitTemplate.convertAndSend("queue-hello", "hello springboot rabbitMq");
	}
	
}
