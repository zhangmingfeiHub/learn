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
	
	public void sendMsg(int type) {
		if (type == 1)
			rabbitTemplate.convertAndSend("queue-hello", "hello springboot rabbitMq");
		else if (type == 2)
			rabbitTemplate.convertAndSend("queue-valid", "test dead queue, 【queue-valid】");
		else if (type == 3)
			rabbitTemplate.convertAndSend("queue-valid-2", "test dead queue, 【queue-valid-2】");
	}
	
}
