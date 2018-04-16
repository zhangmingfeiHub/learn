/**
 * 
 * @author mingfei.z 2018年4月12日 下午9:51:20
 */
package com.mfzhang.mayi.spring.aop.v1.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 定义切面类
 * 1、{@link EnableAspectJAutoProxy} 启用自动代理功能，
 *     自动代理会为使用{@link Aspect} 注解的Bean创建代理，该代理会围绕切点所匹配的目标Bean；
 * 2、{@link Aspect} 注解标注切面类，自动代理仅使用该注解作为创建切面的指导，即自动代理会找到标注有{@link Aspect} 注解的类，并创建切面代理；
 * 3、切面类需创建为Bean，在spring容器中；
 * 
 * 概念
 * 1、连接点：每个可能加入切面功能的地方；
 * 2、切点：所有匹配指示器指定的切点表达式的连接点；
 * 3、通知：在某个切点切入到目标bean接口的功能（或称操作，或称行为，或称要做的事情）；
 * 4、切面：不属于业务逻辑的代码功能，抽取出来模块化（也由切点 和 通知组成）；
 * 5、引入：通过面向切面编程，给现有的类添加新的方法或属性（新的方法或属性在一个类A中，把类A中的方法或属性引入到目标类中，通过代理的方式，不是真实的给目标类里添加方法或属性）；
 * 6、织入：把切面应用到目标对象，并创建代理对象的过程；
 * 
 * @author mingfei.z
 */
@Aspect
@Component("aspectjV1Config")
public class AopAspect {

	private static final Logger logger = LoggerFactory.getLogger(AopAspect.class);
	
	/**
	 * 1、指示器：
	 *     只有execution() 是用来匹配连接点的；
	 *     其他的指示器都是在execution() 匹配的基础上加限制的（缩小范围）；
	 * 
	 * @author mingfei.z
	 */
	@Pointcut(value = "execution(* com.mfzhang.mayi.spring.aop.service.*.*(..))")
	private void pointCut() {}
	
	/**
	 * 前置通知，在目标方法执行前
	 * 
	 * @author mingfei.z
	 */
	@Before("pointCut()")
	public void sayHello() {
		System.err.println("--- hello(v1 config) ---");
		logger.info("say: {}", "hello");
	}
	
	/**
	 * 后置通知，在目标方法成功执行之后
	 * {@link After} 在目标方法执行之后，不论目标方法是否执行成功
	 * 
	 * @author mingfei.z
	 */
	@AfterReturning("pointCut()")
	public void sayBye() {
		System.err.println("--- bye bye(v1 config) ---");
		logger.info("say: {}", "bye bye");
	}
	
	/**
	 * 后置通知，在目标方法发生异常之后
	 * 
	 * @author mingfei.z
	 */
	@AfterThrowing("pointCut()")
	public void sayNo() {
		System.err.println("--- nothing(v1 config) ---");
		logger.info("say: {}", "nothing");
	}
	
}
