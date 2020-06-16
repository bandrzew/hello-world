package com.world.hello.serializer;

import com.world.hello.dto.GreetingDto;
import com.world.hello.model.Greeting;

public class GreetingSerializer {

	public static GreetingDto toDto(Greeting greeting) {
		GreetingDto greetingDto = new GreetingDto();
		greetingDto.setId(greeting.getId());
		greetingDto.setContent(greeting.getContent());

		return greetingDto;
	}

}
