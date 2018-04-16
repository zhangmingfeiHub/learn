/**
 * 
 * @author mingfei.z 2018年4月16日 下午10:20:46
 */
package com.mfzhang.mayi.spring.aop.v3.xml;

/**
 * xml配置实现，为通知传递参数
 * @author mingfei.z
 */
public class AopAspect {

	public void sayHello(Integer userId) {
		System.err.println("--- hello(v3 xml) ---" + userId.toString() );
	}

	public void sayBye(Integer userId) {
		System.err.println("--- bye bye(v3 xml) ---" + userId.toString() );
	}

	public void sayNo(Integer userId) {
		System.err.println("--- say nothing(v3 xml) ---" + userId.toString() );
	}
	
}
