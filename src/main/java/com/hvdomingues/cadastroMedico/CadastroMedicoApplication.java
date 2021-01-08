package com.hvdomingues.cadastroMedico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration  // Sprint Boot Auto Configuration
@ComponentScan(basePackages = "com.hvdomingues.cadastroMedico")
public class CadastroMedicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroMedicoApplication.class, args);
	}

}
