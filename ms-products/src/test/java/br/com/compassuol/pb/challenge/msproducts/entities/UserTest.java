package br.com.compassuol.pb.challenge.msproducts.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testConstructors() {
        User actualUser = new User();
        actualUser.setId(1L);
        actualUser.setEmail("email");
        actualUser.setPassword("password");
        actualUser.setFirstName("first name");
        actualUser.setLastName("last name");
        assertEquals("last name", actualUser.getLastName());
        assertEquals("first name", actualUser.getFirstName());
        assertEquals("password", actualUser.getPassword());
        assertEquals("email", actualUser.getEmail());
        assertEquals(1L, actualUser.getId().longValue());
    }
}