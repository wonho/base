package com.base.business.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {
	  @GetMapping("/anonymous")
	  public String simple1(){
	    return "Anonymous";
	  }
	  
	  @GetMapping("/sample")
	  public String simple2(){
	    return "hi spring boot";
	  }
	  
	  @GetMapping("/sample2")
	  public String simple3(){
		  return "hi spring boot 2222222222222";
	  }
}
