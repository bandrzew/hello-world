package com.world.hello.serializer;

import com.world.hello.dto.GreetingDto;
import com.world.hello.model.Greeting;

public class GreetingSerializer {

	public static GreetingDto toDto(Greeting greeting) {
		GreetingDto greetingDto = new GreetingDto();
		greetingDto.setContent(greeting.getContent());

		int id = greeting.getId();
		greetingDto.setId(id == 0 ? null : id);

		return greetingDto;
	}

}
