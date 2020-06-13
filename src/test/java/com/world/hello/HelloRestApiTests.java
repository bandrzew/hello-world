package com.world.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.world.hello.controller.GreetingController;
import com.world.hello.dto.GreetingDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloRestApiTests {

	@InjectMocks
	private GreetingController greetingController;

	@Test
	void getGreetingShouldSayHello() {
		assertEquals(GreetingDto.DEFAULT_GREETING, greetingController.getGreeting());
	}

}
