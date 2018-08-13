/**
 * 
 * @author mingfei.z 2018年8月11日 下午3:43:18
 */
package com.mfzhang.mayi.dubbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mfzhang.mayi.dubbo.service.DubboProviderService;
import com.mfzhang.mayi.dubbo.service.impl.DubboProviderServiceImpl;

/**
 * 
 * @author mingfei.z
 */
//@Configuration
public class RootConfig {

	@Bean
	public DubboProviderService dubboProviderService() {
		return new DubboProviderServiceImpl();
	}
}
