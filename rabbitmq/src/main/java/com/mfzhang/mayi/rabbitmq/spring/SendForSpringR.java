package com.mfzhang.mayi.rabbitmq.spring;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.mfzhang.mayi.common.constant.CommonConstants;

public class SendForSpringR {

	private static final String EXCHANGE_NAME = "test_exchange_spring";
	
	public static void main(String[] args) {
		
		send1();
		
		// send2();
        
		
	}

	private static void send2() {
		//获取一个连接工厂，用户默认是guest/guest（只能使用部署在本机的RabbitMQ）
        //是Spring实现的对com.rabbitmq.client.Connection的包装
        ConnectionFactory cf = new CachingConnectionFactory("localhost");
        
        //对AMQP 0-9-1的实现
        RabbitAdmin admin = new RabbitAdmin(cf);
        //声明一个exchange
        TopicExchange exchange = new TopicExchange("myExchange2");
        admin.declareExchange(exchange);

        //发送模版，设置上连接工厂
        RabbitTemplate template = new RabbitTemplate(cf);
        //发送消息
        template.convertAndSend("myExchange2", "foo.bar", "Hello, world!");
	}

	private static void send1() {
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
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", message);
		System.err.println(" [x] send message: " + message);
	}

}
