package com.world.hello.dto;

import org.springframework.lang.NonNull;

public class GreetingDto {

	public static final String DEFAULT_GREETING = "Hello World!";

	private Long id;

	@NonNull
	private String content;

	public GreetingDto() {
		content = DEFAULT_GREETING;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
