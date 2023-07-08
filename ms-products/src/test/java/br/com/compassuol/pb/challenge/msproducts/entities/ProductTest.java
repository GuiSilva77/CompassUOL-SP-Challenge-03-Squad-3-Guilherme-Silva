package br.com.compassuol.pb.challenge.msproducts.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testEquals() {
        Product product1 = new Product();
                product1.setId(1L);
        Product product2 = new Product();
                product2.setId(1L);
        Product product3 = new Product();
                product3.setId(2L);

        assertEquals(product1, product2);
        assertNotEquals(product1, product3);
    }

    @Test
    void testHashCode() {
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(1L);

        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void testGettersAndSetters() {
        Product product = new Product();
        product.setId(1L);
        product.setDate(Date.valueOf("2020-01-01"));
        product.setName("name");
        product.setImgUrl("imgUrl");
        product.setDescription("description");
        product.setPrice(BigDecimal.valueOf(1.0));


        assertEquals(1L, product.getId());
        assertEquals("name", product.getName());
        assertEquals("description", product.getDescription());
        assertEquals(BigDecimal.valueOf(1.0), product.getPrice());
        assertEquals("imgUrl", product.getImgUrl());
    }
}