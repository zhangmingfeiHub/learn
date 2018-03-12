package com.mfzhang.mayi.rabbitmq;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * useDefaultFilters=true，表示spring默认扫描指定基础包里的所有含@Component注解的类，注册bean到spring容器中<br/>
 * useDefaultFilters=false，表示不使用默认配置，而是扫描用户自定义的，<br/>
 *     只有设为false，excludeFilters、includeFilters才会生效<br/>
 *     
 * 过滤器的顺序是excludeFilters要优于includeFilters，即两个过滤器配置了相同的，则配置的类或注解不会被扫描<br/>
 * 
 * @author mingfei.z
 */
@Configuration
@ComponentScan(basePackageClasses = Mark.class, useDefaultFilters = false, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
}, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class)
})
public class WebConfig {

}
