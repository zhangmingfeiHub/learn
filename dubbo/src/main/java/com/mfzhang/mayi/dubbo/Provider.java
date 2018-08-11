package com.mfzhang.mayi.dubbo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class Provider {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:dubbo-provider.xml","classpath:applicationContext.xml");
		context.start();
		
		System.in.read(); // 按任意键退出
	}
	
}
