package br.com.compassuol.pb.challenge.msproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class MsProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductsApplication.class, args);
	}

}
