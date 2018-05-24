package com.czh.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.czh.springcloud.ComputeClient;
import com.czh.springcloud.ComputeClient1;
import com.czh.springcloud.InParamsDto;
import com.czh.springcloud.OutParamsDto;

/**  
* <p>Title: TestController.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月14日  
* @version 1.0  
*/
@RestController
public class TestController {
	@Autowired
    ComputeClient computeClient;
	@Autowired
	ComputeClient1 computeClient1;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add() {
        return computeClient.add(10, 20);
    }
    
    @RequestMapping(value = "/add1", method = RequestMethod.GET)
    public Integer add1() {
    	return computeClient1.add(10, 20,30);
    }

    @RequestMapping(value = "/objectTest")
	public OutParamsDto objectTest(InParamsDto inParams) {
		return computeClient1.objectTest(inParams);
	}
}
