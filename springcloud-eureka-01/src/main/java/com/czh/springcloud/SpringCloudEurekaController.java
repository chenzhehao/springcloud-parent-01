package com.czh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * 
 * @author chenzhehao
 *
 *@SpringBootApplication
 *使用了@SpringBootApplication注解的话，系统会去入口类的同级包以及下级包中去扫描实体类
 *
 *@EnableEurekaServer注解启动一个服务注册中心提供给其他应用进行对话
 *
 */
@EnableEurekaServer
@EnableAutoConfiguration
@SpringBootApplication
public class SpringCloudEurekaController extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringCloudEurekaController.class);
    }
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringCloudEurekaController.class, args);
	}

}
