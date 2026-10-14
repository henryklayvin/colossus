package io.github.henryklayvin.colossus;

import org.springframework.boot.SpringApplication;

public class TestColossusApplication {

	public static void main(String[] args) {
		SpringApplication.from(ColossusApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
