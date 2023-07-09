package br.com.compassuol.pb.challenge.msusers.filters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(CsrfCookieFilter.class)
class CsrfCookieFilterTest {

    @Autowired
    SecurityFilterChain filterChain;

    @Test
    void csrfCookieFilter() {
        assertNotNull(filterChain.getFilters().stream().filter(f -> f instanceof CsrfCookieFilter).findFirst());
    }
}