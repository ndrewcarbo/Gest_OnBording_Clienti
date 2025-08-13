package com.example.onBoarding_syst.ConfigSwagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OpenApiController {
	
	@GetMapping("/")
	public String OpenSwaggerAuto() {
		return "redirect:/swagger-ui/index.html";
	}
}
