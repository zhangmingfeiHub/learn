/**
 * 
 * @author mingfei.z 2018年4月19日 下午11:44:07
 */
package com.mfzhang.mayi.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author mingfei.z
 */
public class ObjectUtils {

	public static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);
	
	public static Map<String, Object> objectToMap(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> map = new HashMap<>();
		
		if (null == obj) 
			return map;
		
		Class<?> clazz = obj.getClass();
		logger.info("objectToMap: clazz={}", clazz);
		
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object value = field.get(obj);
			
			map.put(fieldName, value);
		}
		
		return map;
	}
	
}
