package com.example.pipeline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * redirection to swagger api documentation
 */
@Controller
public class ApiDocumentationController {

	@RequestMapping(value = "/api/documents")
	public String apiDocumentation() {
		System.out.println("swagger-ui.html");
		return "redirect:swagger-ui.html";
	}
}