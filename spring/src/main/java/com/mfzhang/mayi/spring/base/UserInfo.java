/**
 * 
 * @author mingfei.z 2018年4月23日 下午7:37:56
 */
package com.mfzhang.mayi.spring.base;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * Bean 生命周期
 * 1、调无参构造方法，实例化Bean；
 * 2、填充属性（如按类型自动注入）；
 * 3、如果实现{@link BeanNameAware} 接口，则调用该接口的setBeanName()方法，并把Bean的ID作为参数传入；
 * 4、如果实现{@link BeanFactoryAware} 接口，则调用该接口的setBeanFactory()方法，并把BeanFactory容器实例传入；
 * 5、如果实现{@link ApplicationContextAware} 接口，则调用该接口setApplicationContext() 方法，并把上下文引用传入；
 * 6.1、如果实现{@link InitializingBean} 接口，则调用该接口afterPropertiesSet() 方法；
 * 6.2、如果使用init-method指定了自定义初始化方法，则该方法在afterPropertiesSet()之后也会被调用；
 * 7、如果实现{@link BeanPostProcessor} 接口，则会先调用postProcessBeforeInitialization() 方法，再调用postProcessAfterInitialization() 方法；
 * 8.1、如果实现{@link DisposableBean} 接口，则调用该接口的destroy()
 * 8.2、如果使用destroy-method指定了自定义的销毁方法，则该方法在destroy() 之后也会被调用；
 * 
 * @author mingfei.z
 */
@Component
public class UserInfo implements Serializable, BeanNameAware, BeanFactoryAware, ApplicationContextAware,
		BeanPostProcessor, InitializingBean, DisposableBean {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Address address;
	
	/**
	 * 1、调无参构造方法，实例化Bean；
	 * 2、填充属性（如按类型自动注入）；
	 * 
	 * @author mingfei.z
	 */
	public UserInfo() {
		logger.info("UserInfo constructor, address: {}" , address);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6981093055944503745L;

	/**
	 * 8.1、如果实现{@link DisposableBean} 接口，则调用该接口的destroy()
	 * 
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 * @author mingfei.z
	 */
	@Override
	public void destroy() throws Exception {
		logger.info("UserInfo initialize method: {}", "destroy()");
	}

	/**
	 * 7、如果实现{@link BeanPostProcessor} 接口，
	 * 则会先调用postProcessBeforeInitialization() 方法，
	 * 再调用postProcessAfterInitialization() 方法；
	 * 
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 * @author mingfei.z
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info("UserInfo initialize method: {}，param：{}-{}", "postProcessBeforeInitialization()", bean, beanName);
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}
	
	/**
	 * 6.1、如果实现{@link InitializingBean} 接口，则调用该接口afterPropertiesSet() 方法；
	 * 
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 * @author mingfei.z
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("UserInfo initialize method: {}", "afterPropertiesSet()");
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info("UserInfo initialize method: {}，param：{}-{}", "postProcessAfterInitialization()", bean, beanName);
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
	
	/**
	 * 5、如果实现{@link ApplicationContextAware} 接口，则调用该接口setApplicationContext() 方法，并把上下文引用传入；
	 * 
	 * (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 * @author mingfei.z
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info("UserInfo initialize method: {}，param：{}", "setApplicationContext()", applicationContext);
	}

	/**
	 * 4、如果实现{@link BeanFactoryAware} 接口，则调用该接口的setBeanFactory()方法，并把BeanFactory容器实例传入；
	 * 
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
	 * @author mingfei.z
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		logger.info("UserInfo initialize method: {}，param：{}", "setBeanFactory()", beanFactory);
	}

	/**
	 * 3、如果实现{@link BeanNameAware} 接口，则调用该接口的setBeanName()方法，并把Bean的ID作为参数传入；
	 * 
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
	 * @author mingfei.z
	 */
	@Override
	public void setBeanName(String name) {
		logger.info("UserInfo initialize method: {}，param：{}", "setBeanName()", name);
	}

	/**
	 * 6.2、如果使用init-method指定了自定义初始化方法，则该方法在afterPropertiesSet()之后也会被调用；
	 * 
	 * @author mingfei.z
	 */
	public void initMethod() {
		logger.info("UserInfo initialize method: {}", "initMethod()");
	}

	/**
	 * 8.2、如果使用destroy-method指定了自定义的销毁方法，则该方法在destroy() 之后也会被调用；
	 * 
	 * @author mingfei.z
	 */
	public void destroyMethod() {
		logger.info("UserInfo initialize method: {}", "destroyMethod()");
	}
	
}
