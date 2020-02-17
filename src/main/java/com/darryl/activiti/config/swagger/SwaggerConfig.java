package com.darryl.activiti.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: Darryl
 * @Description: swagger config
 * @Date: created in 2020/2/16 12:28
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 设置一个开关，生产版本为false，关闭swagger
     */
    @Value("${swagger.ebable}")
    private boolean ebable;

    /**
     * 创建activiti API
     */
    @Bean
    public Docket createRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("工作流")
                // 详细定制
                .apiInfo(apiInfo())
                .enable(ebable)
                .pathMapping("/")
                .select()
                // 指定当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.darryl.activiti.controller"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo()
    {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                .title("系统接口文档")
                .description("用于测试接口")
                .contact("darryl")
                .version("0.0.1")
                .build();
    }

}
