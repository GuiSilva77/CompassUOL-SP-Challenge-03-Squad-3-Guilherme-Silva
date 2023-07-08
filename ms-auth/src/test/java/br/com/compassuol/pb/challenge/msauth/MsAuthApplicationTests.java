package br.com.compassuol.pb.challenge.msauth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MsAuthApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		assertTrue(applicationContext.containsBean("springSecurityFilterChain"));
	}

	@Test
	void testFilter() {
		SecurityFilterChain filterChain = applicationContext.getBean(SecurityFilterChain.class);

		assertTrue(filterChain.getFilters().stream().anyMatch(f -> f.getClass().getName().equals("br.com.compassuol.pb.challenge.msauth.filters.JWTTokenGeneratorFilter")));
	}
}
