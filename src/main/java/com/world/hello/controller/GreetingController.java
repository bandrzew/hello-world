package com.world.hello.controller;

import com.world.hello.dto.GreetingDto;
import com.world.hello.model.Greeting;
import com.world.hello.repository.GreetingRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/greetings")
public class GreetingController {

	private final GreetingRepository greetingRepository;

	@Autowired
	public GreetingController(GreetingRepository greetingRepository) {
		this.greetingRepository = greetingRepository;
	}

	@GetMapping
	public List<String> list() {
		return greetingRepository.findAll().stream().map(Greeting::getContent).collect(Collectors.toList());
	}

	@GetMapping
	public String getGreeting() {
		return GreetingDto.DEFAULT_GREETING;
	}

	@GetMapping(path = "/{id}")
	public String get(@PathVariable("id") Long id) {
		return greetingRepository.findById(id).map(Greeting::getContent).orElseThrow();
	}

	@PutMapping
	public void create(GreetingDto greetingDto) {
		Greeting greeting = new Greeting();
		greeting.setContent(greetingDto.getContent());
		greetingRepository.save(greeting);
	}

	@PostMapping(path = "/{id}")
	public void edit(@PathVariable("id") Long id, GreetingDto greetingDto) {
		Greeting greeting = greetingRepository.findById(id).orElseThrow();
		greeting.setContent(greetingDto.getContent());
		greetingRepository.save(greeting);
	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") Long id) {
		greetingRepository.deleteById(id);
	}

}
