package com.mfzhang.mayi.dubbo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.container.Main;

/**
 * Hello world!
 *
 */
public class Provider {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:applicationContext-*.xml");
		context.start();
		
		System.in.read();
	}
	
}
