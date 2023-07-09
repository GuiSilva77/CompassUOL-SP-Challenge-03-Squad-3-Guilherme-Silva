package br.com.compassuol.pb.challenge.msusers.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(SecurityConfig.class)
class SecurityConfigTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void filterChain() {
        assertNotNull(applicationContext.getBean("springSecurityFilterChain"));
    }

    @Test
    void passwordEncoder() {
        assertNotNull(applicationContext.getBean("passwordEncoder"));
    }

    @Test
    void corsConfiguration() {
        assertNotNull(applicationContext.getBean("getCorsConfiguration"));
    }
}