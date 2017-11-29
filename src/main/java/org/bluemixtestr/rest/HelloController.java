package org.bluemixtestr.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class HelloController {

//	@Autowired private BuildProperties buildProperties;
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping(){
		return "pong";
	}

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String health(){
		return "UP for test";
	}
	
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info() throws Exception{
		return "pong";
	}
	
}
