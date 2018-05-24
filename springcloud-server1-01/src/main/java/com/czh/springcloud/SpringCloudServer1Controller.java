package com.czh.springcloud;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author chenzhehao
 *
 * @SpringBootApplication 使用了@SpringBootApplication注解的话，系统会去入口类的同级包以及下级包中去扫描实体类
 *
 * @EnableEurekaServer注解启动一个服务注册中心提供给其他应用进行对话
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@RestController
@EnableDiscoveryClient
public class SpringCloudServer1Controller extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringCloudServer1Controller.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringCloudServer1Controller.class, args);
	}

	@Autowired
	private DiscoveryClient client;
	private final Logger logger = Logger.getLogger(getClass());
	@RequestMapping(value = "/add1", method = RequestMethod.GET)
	public Integer add1(@RequestParam Integer a, @RequestParam Integer b, @RequestParam Integer c) {
		ServiceInstance instance = client.getLocalServiceInstance();
		Integer r = a + b +c;
		logger.info("/add1, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
		return r;
	}
	
	
	@RequestMapping(value = "/objectTest")
	public OutParamsDto objectTest(InParamsDto inParams) {
		logger.info(inParams);
		OutParamsDto outParams = new OutParamsDto();
		outParams.setReturnCode("000000");
		outParams.setMessage("success");
		outParams.setStatus(0);
		return outParams;
	}
	

}
