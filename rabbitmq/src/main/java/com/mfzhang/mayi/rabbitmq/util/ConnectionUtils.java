package com.mfzhang.mayi.rabbitmq.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtils {

	public static Connection getConn() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("mingfei");
		factory.setPassword("mingfei");
		factory.setVirtualHost("/v-learn");
		
		return factory.newConnection();
	}
	
}
