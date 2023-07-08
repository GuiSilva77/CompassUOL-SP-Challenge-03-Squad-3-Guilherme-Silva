package br.com.compassuol.pb.challenge.msauth.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testConstructor() {
        Role actualRole = new Role();
        actualRole.setId(123L);
        actualRole.setRole("Role");

        assertEquals(123L, actualRole.getId().longValue());
        assertEquals("Role", actualRole.getRole());
    }

    @Test
    void testAllArgsConstructor() {
        Role actualRole = new Role(123L, "Role");

        assertEquals(123L, actualRole.getId().longValue());
        assertEquals("Role", actualRole.getRole());
    }

}