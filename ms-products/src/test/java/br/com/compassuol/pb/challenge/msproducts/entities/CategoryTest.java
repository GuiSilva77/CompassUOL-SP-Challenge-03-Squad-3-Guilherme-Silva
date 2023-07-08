package br.com.compassuol.pb.challenge.msproducts.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
     void testConstructor() {
        Category actualCategory = new Category();
        actualCategory.setId(1L);
        actualCategory.setName("Name");
        assertEquals("Name", actualCategory.getName());
        assertEquals(1L, actualCategory.getId().longValue());
    }

}