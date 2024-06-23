package br.com.vendedormanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication(scanBasePackages = "br.com.vendedormanager")
public class VendedorManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendedorManagerApplication.class, args);
	}

}
