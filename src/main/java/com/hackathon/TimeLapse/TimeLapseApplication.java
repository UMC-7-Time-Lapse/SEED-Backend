package com.hackathon.TimeLapse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TimeLapseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeLapseApplication.class, args);
	}

}
