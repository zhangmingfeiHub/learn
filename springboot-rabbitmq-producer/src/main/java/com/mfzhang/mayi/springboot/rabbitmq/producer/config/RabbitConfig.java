/**
 * 
 * @author mingfei.z 2018年9月27日 下午11:47:26
 */
package com.mfzhang.mayi.springboot.rabbitmq.producer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author mingfei.z
 */
@Configuration
public class RabbitConfig {

	@Bean
	public Queue queue() {
		return new Queue("queue-hello");
	}
	
}
