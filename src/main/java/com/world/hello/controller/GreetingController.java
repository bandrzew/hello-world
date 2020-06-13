package com.world.hello.controller;

import com.world.hello.dto.GreetingDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/greetings")
public class GreetingController {

	@GetMapping
	public String getGreeting() {
		return GreetingDto.DEFAULT_GREETING;
	}

}
