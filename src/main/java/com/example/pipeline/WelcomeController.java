package com.example.pipeline;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping("/")
	public String welcome() {
		return "Welcome to REST API development !, LocalDateTime: " + LocalDateTime.now();
	}

	@GetMapping("/pipeline")
	public String pipeline() {
		return "Hello Spring Boot2 Pipeline!";
	}

}
