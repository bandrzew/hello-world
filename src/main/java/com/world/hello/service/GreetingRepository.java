package com.world.hello.service;

import com.world.hello.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting, Integer> {

}
