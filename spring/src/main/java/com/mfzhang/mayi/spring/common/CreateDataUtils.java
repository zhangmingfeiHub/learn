/**
 * 
 * @author mingfei.z 2018年4月24日 下午10:32:28
 */
package com.mfzhang.mayi.spring.common;

import java.util.ArrayList;
import java.util.List;

import com.mfzhang.mayi.spring.base.vo.AnimalVo;

/**
 * 
 * @author mingfei.z
 */
public class CreateDataUtils {

	/**
	 * 指定数量
	 * 
	 * @author mingfei.z
	 * @param count
	 * @return
	 */
	public static List<AnimalVo> getAnimals(int count) {
		List<AnimalVo> list = new ArrayList<>();
		
		for (int i=1; i<=count; i++) {
			AnimalVo a = new AnimalVo();
			a.setId(i);
			a.setName("name-" + i);
			
			list.add(a);
		}
		
		return list;
	}
	
}
