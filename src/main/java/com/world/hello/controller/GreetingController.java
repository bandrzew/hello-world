package com.world.hello.controller;

import com.world.hello.deserializer.GreetingDeserializer;
import com.world.hello.dto.GreetingDto;
import com.world.hello.serializer.GreetingSerializer;
import com.world.hello.service.GreetingService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/greetings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class GreetingController {

	private final GreetingService greetingService;

	@Autowired
	public GreetingController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@GetMapping
	public List<GreetingDto> list() {
		return greetingService.list().stream().map(GreetingSerializer::toDto).collect(Collectors.toList());
	}

	@GetMapping(path = "/{id}")
	public GreetingDto get(@PathVariable("id") Integer id) {
		return GreetingSerializer.toDto(greetingService.get(id));
	}

	@PutMapping
	public void create(@Valid @RequestBody GreetingDto greetingDto) {
		greetingService.create(GreetingDeserializer.fromDto(greetingDto));
	}

	@PostMapping(path = "/{id}")
	public void edit(@PathVariable("id") Integer id, @Valid @RequestBody GreetingDto greetingDto) {
		greetingService.edit(id, GreetingDeserializer.fromDto(greetingDto));
	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") Integer id) {
		greetingService.delete(id);
	}

}
