package com.world.hello.service;

import com.world.hello.model.Greeting;
import java.util.List;
import java.util.Optional;
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

	public Optional<Greeting> get(Integer id) {
		return greetingRepository.findById(id);
	}

	public Greeting create(Greeting greeting) {
		return greetingRepository.save(greeting);
	}

	public Optional<Greeting> edit(Integer id, Greeting editGreeting) {
		return greetingRepository.findById(id).map(greeting -> {
			greeting.setContent(editGreeting.getContent());
			return greeting;
		}).map(greetingRepository::save);
	}

	public void delete(Integer id) {
		greetingRepository.deleteById(id);
	}

}
