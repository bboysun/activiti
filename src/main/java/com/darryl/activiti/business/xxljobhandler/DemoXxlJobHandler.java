package com.darryl.activiti.business.xxljobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: Darryl
 * @Description: xxl job handler demo
 * @Date: 2020/05/17
 */
@Component
public class DemoXxlJobHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 1、简单任务示例（Bean模式）
	 */
	@XxlJob("demoJobHandler")
	public ReturnT<String> demoJobHandler(String param) throws Exception {
		log.info("demo job handler====start====");
		XxlJobLogger.log("XXL-JOB, Hello World.");
		log.info("demo job handler====end====");
		return ReturnT.SUCCESS;
	}
}
