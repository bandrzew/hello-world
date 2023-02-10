package com.world.hello;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.world.hello.service.GreetingRepository;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DataJpaTest
public class HelloComponentsTest {

	private final DataSource dataSource;

	private final JdbcTemplate jdbcTemplate;

	private final EntityManager entityManager;

	private final GreetingRepository greetingRepository;

	@Autowired
	public HelloComponentsTest(DataSource dataSource, JdbcTemplate jdbcTemplate, EntityManager entityManager,
			GreetingRepository greetingRepository) {
		this.dataSource = dataSource;
		this.jdbcTemplate = jdbcTemplate;
		this.entityManager = entityManager;
		this.greetingRepository = greetingRepository;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	void injectedComponentsAreNotNull() {
		assertNotNull(dataSource);
		assertNotNull(jdbcTemplate);
		assertNotNull(entityManager);
		assertNotNull(greetingRepository);
	}

}
