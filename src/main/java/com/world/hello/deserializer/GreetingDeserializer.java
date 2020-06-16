package com.world.hello.deserializer;

import com.world.hello.dto.GreetingDto;
import com.world.hello.model.Greeting;
import java.util.Optional;

public class GreetingDeserializer {

	public static Greeting fromDto(GreetingDto greetingDto) {
		Greeting greeting = new Greeting();
		Optional.ofNullable(greetingDto.getId()).ifPresent(greeting::setId);
		greeting.setContent(greetingDto.getContent());

		return greeting;
	}

}
