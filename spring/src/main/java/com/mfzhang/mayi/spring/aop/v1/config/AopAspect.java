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
 * 切面原理
 * 1、AspectJ（属静态代理），在编译器把切面应用到目标对象（会运用到特殊的编译器，织入器），生成AOP代理类文件（class文件）；
 * 2、Spring AOP（属动态代理），在运行期把切面应用到目标对象，在运行时内存中创建代理对象，AOP代理类的方法与目标类中方法基本一致，
 *     在方法实现上会把切面的通知织入进来，并调用目标对象的方法，代理类会依赖目标对象，依赖关系有Spring Ioc容器管理；
 *     2.1、通过JDK动态代理方式，针对实现接口的类，Spring AOP会采用针对接口创建代理类，通过反射方式；
 *     2.2、通过CGLIB（代码生成库 code generation library）动态代理，针对没有实现接口的类，CGLIB会创建继承目标类的代理类，代理类中会织入切面的通知；
 *     2.3、Spring框架允许使用AspectJ的主键来定义切面、切点、通知，Spring会识别这些注解并根据这些注解创建AOP代理，
 *         虽然是使用和AspectJ一样的注解，但是底层使用的还是Spring AOP，依然是在运行期创建AOP代理；
 *     2.4、XML配置；
 *     2.5、切面类不会被作为目标类被增强处理；
 *     2.6、代理对象的方法 = 增强处理（通知） + 被代理对象的方法（目标对象的方法）；
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
