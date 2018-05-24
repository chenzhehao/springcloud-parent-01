package com.czh.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
@RestController
@EnableDiscoveryClient
public class SpringCloudRibbonController extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringCloudRibbonController.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringCloudRibbonController.class, args);
	}

	/**
	 * Title: restTemplate
	 * Description:@LoadBalanced注解开启均衡负载能力。
	 * @return
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return restTemplate.getForEntity("http://SPRINGCLOUD-SERVICE/add1?a=10&b=20", String.class).getBody();
    }
    
    @RequestMapping(value = "/add1", method = RequestMethod.GET)
    public String add1() {
        return restTemplate.getForEntity("http://SPRINGCLOUD-SERVICE1/add1?a=100&b=200&c=300", String.class).getBody();
    }
    
    @RequestMapping(value = "/objectTest", method = RequestMethod.GET)
    public OutParamsDto objectTest(InParamsDto inParams) {
    	return restTemplate.getForObject("http://SPRINGCLOUD-SERVICE1/objectTest", OutParamsDto.class, inParams);
    }

}
