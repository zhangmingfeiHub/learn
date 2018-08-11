/**
 * 
 * @author mingfei.z 2018年8月4日 上午2:36:18
 */
package com.mfzhang.mayi.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * @author mingfei.z
 */
@SpringBootApplication
@ImportResource("classpath:spring/dubbo-consumer.xml")
public class Application {

	/**
	 * 
	 * @author mingfei.z
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
