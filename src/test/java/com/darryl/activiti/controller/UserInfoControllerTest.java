package com.darryl.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.darryl.activiti.entity.UserInfoBean;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.regex.Matcher;

/**
 * @Auther: Darryl
 * @Description: spring web mock mvc unit test
 * @Date: 2020/05/01
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserInfoControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //构建MockMVC
	}


	@Test
	public void getUserInfo() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/getUserInfo?id=1")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"name\":\"小明\",\"age\":20,\"skill\":\"画画\",\"evaluate\":\"该学生在画画方面有一定天赋\",\"fraction\":89}"))
				.andReturn();

	}

	@Test
	public void getInfoListPage() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("currentPage", "1");
		params.add("pageSize", "5");

		RequestBuilder request = MockMvcRequestBuilders
				.post("/getInfoListPage")
				.params(params)
				// 不同的请求对应不同的mediaType，如果请求参数使用的是@RequestBody时，需要用application/json 的mediaType
				.accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		mockMvc.perform(request)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotAcceptable())
//				.andExpect(MockMvcResultMatchers.content().string(String.valueOf(Matchers.contains(new UserInfoBean(1L, "小明", 20,
//						"画画", "该学生在画画方面有一定天赋", 89L)))))
				.andReturn();
	}
}
