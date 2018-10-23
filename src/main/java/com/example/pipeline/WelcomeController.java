package com.example.pipeline;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@RequestMapping(value="/welcome/{name}", method=RequestMethod.GET )
	public String welcome(@PathVariable String name) {
		return name+ " welcome to REST API development !, LocalDateTime: " + LocalDateTime.now();
	}

	@RequestMapping(value="/pipeline", method=RequestMethod.GET)
	public String pipeline() {
		return "Hello Spring Boot2 Pipeline!";
	}

}
