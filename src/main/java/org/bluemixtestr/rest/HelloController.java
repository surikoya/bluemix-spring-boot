package org.bluemixtestr.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class HelloController {

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping() {
		return "pong";
	}

}
