package com.darryl.activiti.param;

import com.darryl.activiti.business.impl.ParamServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @Auther: Darryl
 * @Description: 参数化测试
 * @Date: 2020/04/30
 */
@RunWith(Parameterized.class)
public class TestParam {
	private static ParamServiceImpl paramService = new ParamServiceImpl();
	private String param;
	private String result;

	/**
	 * 将测试结果和期望结果，以每一组都是一个数组的形式存放以形成二维数组，转化为list返回并注解。
	 * @return 测试结果和期望结果的集合
	 */
	@Parameterized.Parameters
	public static Collection data() {
		return Arrays.asList(new String[][]{{"darryl","darryl"},{"marrisa","marrisa"}});
	}

	public TestParam(String param, String result) {
		this.param = param;
		this.result = result;
	}

	@Test
	public void testResult() {
		Assert.assertEquals(result, paramService.testParam(param));
	}
}
