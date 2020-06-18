package com.world.hello.service;

import com.world.hello.model.Greeting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

	private final GreetingRepository greetingRepository;

	@Autowired
	public GreetingService(GreetingRepository greetingRepository) {
		this.greetingRepository = greetingRepository;
	}

	public List<Greeting> list() {
		return greetingRepository.findAll();
	}

	public Greeting get(Integer id) {
		return greetingRepository.findById(id).orElseThrow();
	}

	public void create(Greeting greeting) {
		greetingRepository.save(greeting);
	}

	public void edit(Integer id, Greeting editGreeting) {
		greetingRepository.findById(id).map(greeting -> {
			greeting.setContent(editGreeting.getContent());
			return greeting;
		}).map(greetingRepository::save).orElseThrow();
	}

	public void delete(Integer id) {
		greetingRepository.deleteById(id);
	}

}
