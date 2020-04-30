package com.darryl.activiti.business.impl;

import com.darryl.activiti.business.ParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Auther: Darryl
 * @Description: 单元测试---参数化测试
 * @Date: 2020/04/30
 */
@Service
public class ParamServiceImpl implements ParamService {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public String testParam(String name) {
		log.info("test param, name is {}", name);
		return name;
	}
}
