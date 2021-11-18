package com.bootcamp.nttdata.examenServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ExamenServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenServerApplication.class, args);
	}

}
