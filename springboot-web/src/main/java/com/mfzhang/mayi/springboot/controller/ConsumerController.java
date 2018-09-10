/**
 * 
 * @author mingfei.z 2018年8月13日 下午11:41:09
 */
package com.mfzhang.mayi.springboot.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mfzhang.mayi.dubbo.service.DubboProviderService;

/**
 * 
 * @author mingfei.z
 */
@Controller
@RequestMapping("/demo")
public class ConsumerController {

	@Autowired
	private DubboProviderService dubboProviderService;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return dubboProviderService.say("hello");
	}
	
	@RequestMapping("/rest")
	@ResponseBody
	public String rest() {
		
		String accessToken = "13_n9xtp8lSMQN6YTesqkySmX-eRYk_QNM-OyTWPymq9NE1Fj8oJ8HZAwpCPy8raqykUaIe-zbiDISq6eCs5cgyVTSsh-LljZPq8kE3voI5YGQxBsAkbLh_Ad1mcMs2UgF5AyVieJfy3FF8ful0PWOfAHATAS";
		String wxaCodeUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			Map<String,Object> param = new HashMap<>();
			param.put("scene", "12");
			param.put("page", "");
			param.put("width", 430);
			param.put("auto_color", false);
			Map<String,Object> line_color = new HashMap<>();
			line_color.put("r", 0);
			line_color.put("g", 0);
			line_color.put("b", 0);
			param.put("line_color", line_color);
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			HttpEntity requestEntity = new HttpEntity(param, headers);
			ResponseEntity<byte[]> entity = restTemplate.exchange(wxaCodeUrl, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
			if (entity.getStatusCodeValue() == 200) {
				byte[] result = entity.getBody();
				
				inputStream = new ByteArrayInputStream(result);
				
				File file = new File("E:\\mingfei.zhang\\java/1.png");
				if (!file.exists()){
	                file.createNewFile();
	            }
	            outputStream = new FileOutputStream(file);
	            int len = 0;
	            byte[] buf = new byte[1024];
	            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
	                outputStream.write(buf, 0, len);
	            }
	            outputStream.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
		return "suc";
	}
	
}
