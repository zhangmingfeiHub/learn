/**
 * 
 * @author mingfei.z 2018年5月14日 下午10:18:20
 */
package com.mfzhang.mayi.springmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * 根上下文配置类
 * 
 * @author mingfei.z
 */
@Configuration
@ComponentScan(basePackageClasses = Mark.class, useDefaultFilters = false, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Component.class)
}, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Controller.class)
})
public class RootConfig {

}
