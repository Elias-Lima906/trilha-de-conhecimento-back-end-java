package br.com.zup.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class ComicMarvelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicMarvelApplication.class, args);
	}

}
