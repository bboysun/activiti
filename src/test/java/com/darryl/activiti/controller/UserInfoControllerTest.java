package com.darryl.activiti.controller;

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
import org.springframework.web.context.WebApplicationContext;

/**
 * @Auther: Darryl
 * @Description: spring web mvc unit test
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

}
