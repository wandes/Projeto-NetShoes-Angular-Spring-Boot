package br.com.qintess.apinetshoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class ApiNetshoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiNetshoesApplication.class, args);
	}

}
