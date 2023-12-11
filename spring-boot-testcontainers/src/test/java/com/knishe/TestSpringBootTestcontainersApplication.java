package com.knishe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSpringBootTestcontainersApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBootTestcontainersApplication::main).with(TestSpringBootTestcontainersApplication.class).run(args);
	}

}
