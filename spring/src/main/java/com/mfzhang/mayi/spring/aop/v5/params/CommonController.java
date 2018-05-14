/**
 * 
 * @author mingfei.z 2018年4月19日 下午11:18:04
 */
package com.mfzhang.mayi.spring.aop.v5.params;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.mfzhang.mayi.common.util.JsonUtils;
import com.mfzhang.mayi.common.util.ObjectUtils;

/**
 * 
 * @author mingfei.z
 */
@Component
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	public void checkAuth(Object[] args) throws IllegalArgumentException, IllegalAccessException {
		logger.info("---权限校验，入参={}---", JsonUtils.writeValueAsString(args));
		
		if (null != args) {

			for (Object obj : args) {
				Map<String, Object> map = ObjectUtils.objectToMap(obj);
				logger.info("---map: {}---", map);
				
				Integer shopId = (Integer) map.get("shopId");
				Integer userId = (Integer) map.get("userId");
				String userName = (String) map.get("userName");
				logger.info("---shopId={}, userId={}, userName={}---", shopId, userId, userName);
			}
			
		}
	}
	
}
