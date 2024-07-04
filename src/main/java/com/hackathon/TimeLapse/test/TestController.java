package com.hackathon.TimeLapse.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API", description = "Test API 입니다.")
@RequestMapping("/test")
@RestController
public class TestController {

	@GetMapping
	public String test() {
		return "test123";
	}
}
