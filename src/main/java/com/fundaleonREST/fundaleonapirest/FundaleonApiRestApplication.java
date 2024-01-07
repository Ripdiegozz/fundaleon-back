package com.fundaleonREST.fundaleonapirest;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FundaleonApiRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(FundaleonApiRestApplication.class, args);
	}

	@Bean
	public Dotenv dotenv() {
		return Dotenv.configure().load();
	}
}
