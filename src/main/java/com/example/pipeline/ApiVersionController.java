package com.example.pipeline;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiVersionController {

	@RequestMapping(value="/version", method=RequestMethod.GET)
	public String getVersion() {
		return "v1.0";
	}

}
