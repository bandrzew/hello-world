package com.world.hello.controller;

import com.world.hello.deserializer.GreetingDeserializer;
import com.world.hello.dto.GreetingDto;
import com.world.hello.serializer.GreetingSerializer;
import com.world.hello.service.GreetingService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<GreetingDto> get(@PathVariable("id") Integer id) {
		return greetingService.get(id)
				.map(GreetingSerializer::toDto)
				.map(greetingDto -> new ResponseEntity<>(greetingDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping
	public ResponseEntity<GreetingDto> create(@Valid @RequestBody GreetingDto greetingDto) {
		GreetingDto created = GreetingSerializer.toDto(greetingService.create(GreetingDeserializer.fromDto(greetingDto)));
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@PostMapping(path = "/{id}")
	public ResponseEntity<GreetingDto> edit(@PathVariable("id") Integer id, @Valid @RequestBody GreetingDto edited) {
		return greetingService.edit(id, GreetingDeserializer.fromDto(edited))
				.map(GreetingSerializer::toDto)
				.map(greetingDto -> new ResponseEntity<>(greetingDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<GreetingDto> delete(@PathVariable("id") Integer id) {
		greetingService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
