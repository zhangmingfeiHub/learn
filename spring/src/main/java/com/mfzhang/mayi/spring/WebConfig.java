/**
 * 
 * @author mingfei.z 2018年4月10日 下午11:10:54
 */
package com.mfzhang.mayi.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author mingfei.z
 */
@Configuration
@ComponentScan(basePackageClasses = Mark.class, useDefaultFilters = false, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Component.class)
}, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Controller.class)
})
public class WebConfig {

}
