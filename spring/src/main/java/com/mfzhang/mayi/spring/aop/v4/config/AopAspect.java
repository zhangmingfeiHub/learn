/**
 * 
 * @author mingfei.z 2018年4月16日 下午7:40:00
 */
package com.mfzhang.mayi.spring.aop.v4.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

import com.mfzhang.mayi.spring.aop.service.AopService;
import com.mfzhang.mayi.spring.aop.service.IntroduceService;
import com.mfzhang.mayi.spring.aop.service.impl.IntroduceServiceImpl;

/**
 * 
 * @author mingfei.z
 */
//@Aspect
@Component("aopAspectV4Config")
public class AopAspect {

	/**
	 * 通过切面实现引入功能，即给一个类在不需要修改代码的情况下，引入新的属性或方法；
	 * 
	 * 给 {@link AopService} 接口所有的实现类（+表示接口实现类），引入新的类{@link IntroduceServiceImpl}（含类中的属性 和 方法）;
	 * 
	 * @DeclareParents 注解的两个属性：
	 * 1、value：需要引入方法的类；
	 * 2、defaultImpl：指定静态变量introduceService 的 实现类，即把该实现类引入到AopService的实现类中；
	 * 
	 * 切面的作用只是用来把一个类中的方法或属性，引入到另一个类中；
	 * 
	 * 调用时可采取这种方式：((IntroduceService) aopService).add(null);
	 * 
	 */
	@DeclareParents(value = "com.mfzhang.mayi.spring.aop.service.AopService+", 
			defaultImpl = IntroduceServiceImpl.class)
	public static IntroduceService introduceService;
	
}
