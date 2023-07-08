package br.com.compassuol.pb.challenge.msproducts.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    @Test
    void testConstructor() {
        Role actualRole = new Role();
        actualRole.setId(1L);
        actualRole.setRole("Role");
        assertEquals("Role", actualRole.getRole());
        assertEquals(1L, actualRole.getId().longValue());
    }
}