package com.tesoramobil.aportacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class AportacionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AportacionServiceApplication.class, args);
	}

}
