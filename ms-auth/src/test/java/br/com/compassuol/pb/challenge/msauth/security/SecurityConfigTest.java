package br.com.compassuol.pb.challenge.msauth.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(SecurityConfig.class)
class SecurityConfigTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void securityFilterChain() {
        assertNotNull(applicationContext.getBean("securityFilterChain"));
    }

    @Test
    void passwordEncoder() {
        assertNotNull(applicationContext.getBean("passwordEncoder"));
    }

    @Test
    void getCorsConfiguration() {
        assertNotNull(applicationContext.getBean("getCorsConfiguration"));
    }
}