package br.com.compassuol.pb.challenge.msproducts.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.junit.jupiter.api.Assertions.*;

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