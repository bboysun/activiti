package com.darryl.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.darryl.activiti.entity.UserInfoBean;
import com.google.common.collect.Maps;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

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

	@Test
	public void getInfoListPage() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, Integer> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("currentPage", 0);
		requestBody.add("pageSize", 5);
		HttpEntity<MultiValueMap<String, Integer>> requestEntity = new HttpEntity<>(requestBody, headers);
		IPage iPageResponse = restTemplate.postForObject("/getInfoListPage", requestEntity, IPage.class);
		Assert.assertEquals(iPageResponse.getRecords().size(), 5);
		//System.out.println("response is " + iPageResponse.getRecords());
//		List<UserInfoBean> userInfoBeans = JSON.parseArray(iPageResponse.getRecords(), UserInfoBean.class);
//		Assertions.assertThat(userInfoBeans).contains(new UserInfoBean(1L, "小明", 20, "画画",
//				"该学生在画画方面有一定天赋", 89L));
	}


}
