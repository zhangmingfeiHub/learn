/**
 * 
 * @author mingfei.z 2018年9月28日 上午12:01:23
 */
package com.mfzhang.mayi.springboot.rabbitmq.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mfzhang.mayi.springboot.rabbitmq.producer.component.MqProducer;

/**
 * 
 * @author mingfei.z
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootRabbitMqProducer.class)
public class MqProducerTest {

	@Autowired
	private MqProducer mqProducer;
	
	@Test
	public void testSendMsg() {
		mqProducer.sendMsg(1);
		mqProducer.sendMsg(2);
		mqProducer.sendMsg(3);
	}
	
}
