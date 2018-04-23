/**
 * 
 * @author mingfei.z 2018年4月23日 下午7:48:12
 */
package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mfzhang.mayi.spring.WebConfig;
import com.mfzhang.mayi.spring.WebMvcConfig;
import com.mfzhang.mayi.spring.base.UserInfo;

/**
 * 
 * @author mingfei.z
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class TestClazz {

	@Autowired
	private UserInfo userInfo;
	
	@Test
	public void test() {
		System.err.println(userInfo);
	}
	
}
