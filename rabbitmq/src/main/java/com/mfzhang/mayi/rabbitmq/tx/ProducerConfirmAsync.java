/**
 * 
 * @author mingfei.z 2018年3月10日 下午10:51:27
 */
package com.mfzhang.mayi.rabbitmq.tx;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

import com.mfzhang.mayi.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

/**
 * Confirm模式，异步方式<br/>
 * 
 * 原理：channel提供ConfirmListener() 回调方法中只包含deliveryTag（当前发送的消息的序号，即消息ID）<br/>
 * 我们需要自己为每个channel维护一个unconfirm消息序号集合<br/>
 * channel每发送一条消息，则把消息序号加入到unconfirm集合中，当回调handleAck方法，则把unconfirm集合中相应的消息序号删除掉<br/>
 * 从运行效率上看，unconfirm集合最后采用SortedSet存储结构<br/>
 * 
 * @author mingfei.z
 */
public class ProducerConfirmAsync {

	private static final String QUEUE_NAME = "test_queue_tx_confirm_async";
	
	/**
	 * 
	 * @author mingfei.z
	 * @param args
	 * @throws TimeoutException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection connection = ConnectionUtils.getConn();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String message = "hello queue confirm async.";
		
		// 设置信道为confirm模式
		channel.confirmSelect();
		
		// 为channel维护的unconfirm集合
		SortedSet<Long> unConfirmSet = Collections.synchronizedSortedSet(new TreeSet<>());
		
		// 为channel添加回调监听
		// 异步的，生产者可以继续执行下面的代码，rabbitMq确认消息发成功或失败后会异步回调handleAck 或 handleNack
		channel.addConfirmListener(new ConfirmListener() {
			
			/**
			 * 发送失败，执行该回调方法
			 * 
			 * @author mingfei.z
			 * @param deliveryTag
			 * @param multiple
			 * @throws IOException
			 */
			@Override
			public void handleNack(long deliveryTag, boolean multiple) throws IOException {
				if (multiple) {
					System.err.println(" [x] send message fail, callback--multiple");
					unConfirmSet.headSet(deliveryTag+1).clear();
				} else {
					System.err.println(" [x] send message fail, callback");
					unConfirmSet.remove(deliveryTag);
				}
			}
			
			/**
			 * 发送成功，执行该回调方法
			 * 
			 * @author mingfei.z
			 * @param deliveryTag
			 * @param multiple
			 * @throws IOException
			 */
			@Override
			public void handleAck(long deliveryTag, boolean multiple) throws IOException {
				if (multiple) {
					System.err.println(" [x] send message success, callback--multiple");
					unConfirmSet.headSet(deliveryTag+1).clear();
				} else {
					System.err.println(" [x] send message success, callback");
					unConfirmSet.remove(deliveryTag);
				}
			}
		});
		
		// 不停发送消息，测试用
		while (true) {
			// 获取发送消息的序号
			long seqNo = channel.getNextPublishSeqNo();
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			unConfirmSet.add(seqNo);
			System.err.println(" [x] send message: " + message);
			
			Thread.sleep(1500);
		}
		
	}

}
