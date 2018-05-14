/**
 * 
 * @author mingfei.z 2018年4月24日 下午10:23:18
 */
package com.mfzhang.mayi.spring.base.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mfzhang.mayi.common.util.JsonUtils;
import com.mfzhang.mayi.spring.base.service.SpringAutoWiredService;
import com.mfzhang.mayi.spring.base.vo.AnimalVo;
import com.mfzhang.mayi.spring.common.CreateDataUtils;

/**
 * 
 * @author mingfei.z
 */
@Service("autoWiredService")
public class SpringAutoWiredServiceImpl implements SpringAutoWiredService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 * @author mingfei.z
	 * @param id
	 * @return
	 */
	@Override
	public AnimalVo get(Integer id) {
		
		List<AnimalVo> list = CreateDataUtils.getAnimals(20);
		AnimalVo vo = list.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
		
		logger.info("获取动物信息，入参={}，结果={}", id.toString(), JsonUtils.writeValueAsString(vo));
		return vo;
	}
	
	

}
