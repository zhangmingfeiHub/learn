package com.mfzhang.mayi.rabbitmq.spring;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import com.mfzhang.mayi.common.constant.CommonConstants;

public class RecForSpringR {

	private static final String EXCHANGE_NAME = "test_exchange_spring";
	private static final String QUEUE_NAME = "test_queue_spring";
	private static final String ROUTING_KEY = "test_exchange_queue_spring_routing_key";
	
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
		FanoutExchange exchange = new FanoutExchange(EXCHANGE_NAME, false, false);
		admin.declareExchange(exchange);
		
		// 声明队列
		Queue queue = new Queue(QUEUE_NAME);
		admin.declareQueue(queue);
		
		// exchange 绑 queue
		admin.declareBinding(BindingBuilder.bind(queue).to(exchange));
		
		System.err.println(" [x] consumer wait for message. ");
		
		// 监听容器
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
		
		// 监听者
		Object listener = new Object() {
			public void handleMessage(String message) {
				System.err.println(" [x] consumer recieve message: " + message);
			}
		};
		
		// 通过适配器代理listener
		MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
		
		// 把适配器(listener)设置给监听容器
		container.setMessageListener(adapter);
		
		// 设置监听的容器
		container.setQueues(queue);
		
		// 开始监听
		container.start();
		
	}

}
