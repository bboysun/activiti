package com.darryl.activiti.controller;

import com.darryl.activiti.entity.UserInfoBean;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: Darryl
 * @Description: controller unit test
 * @Date: 2020/05/01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserInfoControllerUnitTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getFirstUserInfo() {
		UserInfoBean response = restTemplate.getForObject("/getFirstUserInfo", UserInfoBean.class);
		Assertions.assertThat(response).isEqualTo(new UserInfoBean(1L, "小明", 20, "画画",
				"该学生在画画方面有一定天赋", 89L));
	}

	@Test
	public void getUserInfo() {
		UserInfoBean response = restTemplate.getForObject("/getUserInfo?id=1", UserInfoBean.class);
		Assertions.assertThat(response).isEqualTo(new UserInfoBean(1L, "小明", 20, "画画",
				"该学生在画画方面有一定天赋", 89L));
	}

}
