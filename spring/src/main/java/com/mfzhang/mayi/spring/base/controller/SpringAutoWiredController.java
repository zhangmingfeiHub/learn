/**
 * 
 * @author mingfei.z 2018年4月24日 下午10:23:40
 */
package com.mfzhang.mayi.spring.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.spring.base.service.SpringAutoWiredService;
import com.mfzhang.mayi.spring.base.vo.AnimalVo;

/**
 * 注意：
 * 1、@Autowired(required = false)，required属性在5.0似乎不起作用，××××××××× 该条错误；
 * 2、@Inject，待测试；
 * 3、当有构造函数时，即使没有加注解@Autowired，也会自动注入，
 *     解释：在Bean初始化时会调构造方法，
 *     3.1、如果同时存在空的构造方法 和 可注入属性作为参数的构造方法，则会调空的构造方法，
 *     3.2、如果只存在有参数的构造方法，则会调该构造方法初始化；
 *     3.3、如果同时存在空的构造方法 和 可注入属性作为参数的构造方法，并有参数的构造方法加了@Autowired，则在Bean初始化时只调有参数的构造方法；
 *     3.4、如果同时存在空的构造方法 和 可注入属性作为参数的构造方法（可多个），但有参数的构造方法上没加@Autowired，但是有其他非构造函数的方法加了@Autowired，
 *         则空的构造方法会被调取，并调取加了@Autowired非构造函数的方法进行注入；
 * 4、多个添加@Autowired注解的方法，在Bean初始化填充属性时都会调取；
 * 5、当没有找到可注入的属性时，则会报异常，但如果给注解@Autowired 设置required属性为false，则不会报异常，不过是null的，会报空指针异常；
 * 
 * @author mingfei.z
 */
@Controller("autowiredController")
@RequestMapping("/autowired")
public class SpringAutoWiredController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

//	@Autowired(required = false)
	private SpringAutoWiredService springAutoWiredService;
	
	public SpringAutoWiredController() {
		logger.info("SpringAutoWiredController构造器 自动装配-空");
	}

//	@Autowired
	public SpringAutoWiredController(SpringAutoWiredService springAutoWiredService) {
		logger.info("SpringAutoWiredController构造器 自动装配");
		this.springAutoWiredService = springAutoWiredService;
	}

	/**
	 * @return the springAutoWiredService
	 */
	public SpringAutoWiredService getSpringAutoWiredService() {
		return springAutoWiredService;
	}

	/**
	 * @param springAutoWiredService the springAutoWiredService to set
	 */
	@Autowired(required = false)
	public void setSpringAutoWiredService(SpringAutoWiredService springAutoWiredService) {
		logger.info("setSpringAutoWiredService 自动装配");
		this.springAutoWiredService = springAutoWiredService;
	}
	
//	@Autowired(required = false)
	public void autoWiredService(SpringAutoWiredService springAutoWiredService) {
		logger.info("autoWiredService 自动装配");
		this.springAutoWiredService = springAutoWiredService;
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result<AnimalVo> get(@PathVariable Integer id) {
		AnimalVo vo = springAutoWiredService.get(id);
		
		return Result.success(vo);
	}

}
