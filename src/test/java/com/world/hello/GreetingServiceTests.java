package com.world.hello;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.world.hello.model.Greeting;
import com.world.hello.service.GreetingRepository;
import com.world.hello.service.GreetingService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

@DataJpaTest
class GreetingServiceTests {

	private static final String EXAMPLE_GREETING = "Hello World!";

	private static final String ANOTHER_GREETING = "Ciao Mondo!";

	private final GreetingRepository greetingRepository;

	private final GreetingService greetingService;

	@Autowired
	public GreetingServiceTests(GreetingRepository greetingRepository) {
		this.greetingRepository = greetingRepository;
		this.greetingService = new GreetingService(greetingRepository);
	}

	@Test
	public void addingValidGreetingShouldSucceed() {
		assertDoesNotThrow(() -> greetingService.create(createGreeting()));
		assertFalse(greetingRepository.findAll().isEmpty());
	}

	@Test
	public void addingInvalidGreetingShouldThrowException() {
		assertThrows(ConstraintViolationException.class, () -> greetingService.create(new Greeting()));
	}

	@Test
	public void gettingExistingGreetingShouldSucceed() {
		int id = greetingRepository.save(createGreeting()).getId();
		assertDoesNotThrow(() -> greetingService.get(id));
	}

	@Test
	public void gettingNotExistingGreetingShouldThrowException() {
		int id = greetingRepository.save(createGreeting()).getId();
		assertNull(greetingService.get(id + 1).orElse(null));
	}

	@Test
	public void validEditOfGreetingShouldSucceed() {
		int id = greetingRepository.save(createGreeting()).getId();

		Greeting greeting = new Greeting();
		greeting.setContent(ANOTHER_GREETING);

		assertDoesNotThrow(() -> greetingService.edit(id, greeting));
		assertEquals(ANOTHER_GREETING, greetingRepository.findById(id).map(Greeting::getContent).orElseThrow());
	}

	@Test
	public void invalidEditOfGreetingShouldThrowException() {
		int id = greetingRepository.save(createGreeting()).getId();
		assertThrows(ConstraintViolationException.class, () -> {
			greetingService.edit(id, new Greeting());
			greetingRepository.flush();
		});
	}

	@Test
	public void removingExistingGreetingShouldSucceed() {
		int id = greetingRepository.save(createGreeting()).getId();
		assertDoesNotThrow(() -> greetingService.delete(id));
		assertTrue(greetingRepository.findAll().isEmpty());
	}

	@Test
	public void removingNotExistingGreetingShouldThrowException() {
		int id = greetingRepository.save(createGreeting()).getId();
		assertThrows(EmptyResultDataAccessException.class, () -> greetingService.delete(id + 1));
		assertFalse(greetingRepository.findAll().isEmpty());
	}

	private Greeting createGreeting() {
		Greeting greeting = new Greeting();
		greeting.setContent(EXAMPLE_GREETING);

		return greeting;
	}

}
