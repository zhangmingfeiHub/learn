/**
 * 
 * @author mingfei.z 2018年8月11日 下午4:09:13
 */
package com.mfzhang.mayi.dubbo.service.impl;

import org.springframework.stereotype.Service;

import com.mfzhang.mayi.dubbo.service.DubboProviderService;

/**
 * 
 * @author mingfei.z
 */
//@Service("dubboProviderService")
public class DubboProviderServiceImpl implements DubboProviderService {

	@Override
	public String say(String content) {
		
		String result = "接收到信息：" + content + "，反馈信息：你很棒哦";
		
		return result;
	}

}
