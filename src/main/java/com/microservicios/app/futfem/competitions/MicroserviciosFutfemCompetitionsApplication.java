package com.microservicios.app.futfem.competitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosFutfemCompetitionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosFutfemCompetitionsApplication.class, args);
	}

}
