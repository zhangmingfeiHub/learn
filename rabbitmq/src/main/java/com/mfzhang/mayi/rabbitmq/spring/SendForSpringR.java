package com.mfzhang.mayi.rabbitmq.spring;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.mfzhang.mayi.common.constant.CommonConstants;

public class SendForSpringR {

	private static final String EXCHANGE_NAME = "test_exchange_spring";
	
	public static void main(String[] args) {
		
		// 获取一个连接工厂
		// 是Spring实现的对com.rabbitmq.client.Connection的包装
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setUsername("mingfei");
		factory.setPassword("mingfei");
		factory.setHost("127.0.0.1");
		factory.setPort(5672);
		factory.setVirtualHost(CommonConstants.VHost.HOST_V_LEARN);
		
		RabbitAdmin admin = new RabbitAdmin(factory);

		// 声明exchange
		Exchange exchange = new FanoutExchange(EXCHANGE_NAME, false, false);
		admin.declareExchange(exchange);
		
		String message = "hell spring rabbitmq.";
		
		// 发送模版，设置上连接工厂
		RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
		rabbitTemplate.convertAndSend(message);
		System.err.println(" [x] send message: " + message);
		
	}

}
