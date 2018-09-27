/**
 * 
 * @author mingfei.z 2018年9月27日 下午11:47:26
 */
package com.mfzhang.mayi.springboot.rabbitmq.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author mingfei.z
 */
@Configuration
public class RabbitConfig {

	@Bean
	public Queue helloQueue() {
		return new Queue("queue-hello");
	}
	
	@Bean("validExchange")
	public Exchange validExchange() {
		return ExchangeBuilder.directExchange("exchange-valid").durable(true).build();
	}
	
	@Bean("validQueue")
	public Queue validQueue() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-dead-letter-exchange", "exchange-valid");
		args.put("x-dead-letter-routing-key", "queue-valid-dead-key");
		
		return QueueBuilder.durable("queue-valid").withArguments(args).build();
	}

	@Bean("validDeadQueue")
	public Queue validDeadQueue() {
		
		return QueueBuilder.durable("queue-valid-dead").build();
	}
	
	@Bean
	public Binding validBinding() {
		return new Binding("queue-valid", DestinationType.QUEUE, "exchange-valid", "queue-valid-key", null);
	}

	@Bean
	public Binding validDeadBinding() {
		return new Binding("queue-valid-dead", DestinationType.QUEUE, "exchange-valid", "queue-valid-dead-key", null);
	}

	@Bean("validQueue2")
	public Queue validQueue2() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-dead-letter-exchange", "exchange-valid");
		args.put("x-dead-letter-routing-key", "queue-valid-dead-key-2");
		
		return QueueBuilder.durable("queue-valid-2").withArguments(args).build();
	}

	@Bean("validDeadQueue2")
	public Queue validDeadQueue2() {
		
		return QueueBuilder.durable("queue-valid-dead-2").build();
	}

	@Bean
	public Binding validBinding2() {
		return new Binding("queue-valid-2", DestinationType.QUEUE, "exchange-valid", "queue-valid-key-2", null);
	}

	@Bean
	public Binding validDeadBinding2() {
		return new Binding("queue-valid-dead-2", DestinationType.QUEUE, "exchange-valid", "queue-valid-dead-key-2", null);
	}

}
