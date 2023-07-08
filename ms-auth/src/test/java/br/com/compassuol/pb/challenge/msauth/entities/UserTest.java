package br.com.compassuol.pb.challenge.msauth.entities;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testConstructor() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("name");
        user.setLastName("last");
        user.setEmail("email");
        user.setPassword("password");
        user.setRoles(Set.of(new Role(1L, "role")));

        assertEquals(1L, user.getId());
        assertEquals("name", user.getFirstName());
        assertEquals("last", user.getLastName());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(1, user.getRoles().size());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User(1L, "name", "last", "email", "password", Set.of(new Role(1L, "role")));

        assertEquals(1L, user.getId());
        assertEquals("name", user.getFirstName());
        assertEquals("last", user.getLastName());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(1, user.getRoles().size());
    }
}