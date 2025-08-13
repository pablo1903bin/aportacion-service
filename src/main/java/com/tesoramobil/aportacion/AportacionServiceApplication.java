package com.tesoramobil.aportacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AportacionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AportacionServiceApplication.class, args);
	}

}
