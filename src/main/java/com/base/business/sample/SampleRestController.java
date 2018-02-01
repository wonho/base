package com.base.business.sample;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.base.system.util.MessageUtil.MessageUtil;

@RestController
public class SampleRestController {

	Logger logger = LoggerFactory.getLogger(SampleRestController.class);
	
	@Autowired
	MessageUtil messageUtilService;
	
	@RequestMapping(value="/sample/getdata",method=RequestMethod.GET)
	public Map<String,Object> getData(@RequestParam Map<String,Object> search) {
		// todo
		return  null;		
	}
}
