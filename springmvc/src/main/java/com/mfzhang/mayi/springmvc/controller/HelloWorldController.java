/**
 * 
 * @author mingfei.z 2018年5月14日 下午10:11:48
 */
package com.mfzhang.mayi.springmvc.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mfzhang.mayi.common.Result;
import com.mfzhang.mayi.common.domain.JsonResult;
import com.mfzhang.mayi.common.exception.CustomException;

/**
 * 
 * @author mingfei.z
 */
@Controller
@RequestMapping("hw")
public class HelloWorldController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "say", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> sayHello(@RequestParam("sth") String sth) {
		int i = 1/0;
		
		return JsonResult.success("hello, " + sth);
	}

	@RequestMapping(value = "hot", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> hot(Integer a) {
		
		try {
			int i = 1/a;
		} catch (Exception e) {
			logger.error("hot 时异常", e);
			throw new CustomException(e.getMessage(), "hot 时异常");
		}
		
		return JsonResult.success("hello, it's very hot.");
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "错了哦")
	@RequestMapping(value = "cool", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> cool(Integer a) {
		
		try {
			int i = 1/a;
		} catch (Exception e) {
			logger.error("cool 时异常", e);
			throw new CustomException(e.getMessage(), "cool 时异常");
		}
		
		return JsonResult.success("hello, it's very cool.");
	}
	
	@RequestMapping("/toRedirect")
	public String toRedirect(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		/*String params = "ABC";
		System.err.println("toRedirect: " + params);
		
		FlashMap flashMap = (FlashMap) request.getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE);
		flashMap.put("params", params);
		
		ServletRequestAttributes srAttrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		FlashMap flashMap2 = (FlashMap) srAttrs.getRequest().getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE);
		flashMap2.put("params2", "ABC2");*/
		
		// 放在FlashMap 中，跳转到的方法用Model接收
		redirectAttributes.addFlashAttribute("params", "ABC");
		redirectAttributes.addFlashAttribute("params2", "ABC2");
		redirectAttributes.addFlashAttribute("params3", "ABC3");
		
		// 参数会跟在跳转到的方法url后面，跳转到的放用request.getParameter("")接收
		redirectAttributes.addAttribute("params4", "ABC4");
		
		return "redirect:redirect";
	}
	
	@RequestMapping("/redirect")
	@ResponseBody
	public JsonResult<Map<String, Object>> redirect(HttpServletRequest request, Model model/*, RedirectAttributes redirectAttributes*/) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		String params = request.getParameter("params");
		String params2 = request.getParameter("params2");
		String params3 = request.getParameter("params3");
		String params4 = request.getParameter("params4");
		resultMap.put("attrs-params", params);
		resultMap.put("attrs-params2", params2);
		resultMap.put("attrs-params3", params3);
		resultMap.put("attrs-params4", params4);
		
		Map<String, Object> map = model.asMap();
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			resultMap.put("map-" + key, entry.getValue());
		}
		
		/*Map<String, Object> attrs = redirectAttributes.asMap();
		Iterator<Entry<String, Object>> attrsIt = attrs.entrySet().iterator();
		while (attrsIt.hasNext()) {
			Entry<String, Object> entry = attrsIt.next();
			String key = entry.getKey();
			resultMap.put("flashMapAttrs-" + key, entry.getValue());
		}*/
		
		return JsonResult.success(resultMap);
	}
	
}
