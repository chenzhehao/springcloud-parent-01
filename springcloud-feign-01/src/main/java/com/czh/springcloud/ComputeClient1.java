package com.czh.springcloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**  
* <p>Title: ComputeClient.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月14日  
* @version 1.0  
*/
@FeignClient("springcloud-service1")
public interface ComputeClient1 {

    @RequestMapping(method = RequestMethod.GET, value = "/add1")
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b, @RequestParam(value = "c") Integer c);

    @RequestMapping(value = "/objectTest")
    public OutParamsDto objectTest(InParamsDto inParams) ;
}