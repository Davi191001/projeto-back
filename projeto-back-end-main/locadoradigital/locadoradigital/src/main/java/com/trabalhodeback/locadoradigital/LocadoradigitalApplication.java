package com.trabalhodeback.locadoradigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Locadora Digital API",
				version = "1.0",
				description = "API para gestão de uma locadora digital"
		)
)

public class LocadoradigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocadoradigitalApplication.class, args);
	}

}
