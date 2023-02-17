package com.detrasoft.character;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.detrasoft"})
@EntityScan(basePackages = { "com.detrasoft" })
@EnableJpaRepositories(basePackages = { "com.detrasoft" })
public class CharacterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharacterApiApplication.class, args);
	}

}
