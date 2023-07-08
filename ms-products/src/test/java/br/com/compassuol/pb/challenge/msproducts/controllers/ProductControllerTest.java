package br.com.compassuol.pb.challenge.msproducts.controllers;

import br.com.compassuol.pb.challenge.msproducts.config.TestSecurityConfig;
import br.com.compassuol.pb.challenge.msproducts.entities.Category;
import br.com.compassuol.pb.challenge.msproducts.entities.Product;
import br.com.compassuol.pb.challenge.msproducts.entities.dto.CategoryDto;
import br.com.compassuol.pb.challenge.msproducts.entities.dto.ProductDto;
import br.com.compassuol.pb.challenge.msproducts.entities.payload.ProductPayload;
import br.com.compassuol.pb.challenge.msproducts.exceptions.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.msproducts.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.SecurityContext;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
@Import(TestSecurityConfig.class)
class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    Product product;
    ProductDto productDto;

    @BeforeEach
    void setUp() {
        product = new Product(new Date(), "Description", "Product 1", "https://url.com", BigDecimal.valueOf(156), Set.of(new Category(1L, "Category 1"), new Category(2L, "Category 2")));
        productDto = new ProductDto(new Date(), "Description", "Product 1", "https://url.com", BigDecimal.valueOf(156), Set.of(new CategoryDto(1L), new CategoryDto(2L)));
    }

    @AfterEach
    void tearDown() {
        reset(productService);
    }

    @Test
    void getProducts() throws Exception {
        when(productService.findAll(0, 10, "id", "ASC")).thenReturn(new ProductPayload(List.of(productDto), 1, 1, 1, 1, true));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.products[0].name").value(productDto.name()))
                .andExpect(jsonPath("$.products[0].description").value(productDto.description()))
                .andExpect(jsonPath("$.products[0].imgUrl").value(productDto.imgUrl()));
    }

    @Test
    void getProductById() throws Exception {
        when(productService.findProductById(1L)).thenReturn(productDto);
        when(productService.findProductById(2L)).thenThrow(new ResourceNotFoundException("Product", "id", "2"));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(productDto.name()))
                .andExpect(jsonPath("$.description").value(productDto.description()))
                .andExpect(jsonPath("$.imgUrl").value(productDto.imgUrl()))
                .andExpect(jsonPath("$.price").value(productDto.price()))
                .andExpect(jsonPath("$.categories[0].id").value(productDto.categories().stream().findFirst().get().id()))
                .andExpect(jsonPath("$.categories[1].id").value(productDto.categories().stream().skip(1).findFirst().get().id()));

        mockMvc.perform(get("/products/2"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Product not found with id: '2'"));
    }

    @Test
    void createProductById() throws Exception {
        when(productService.addProduct(any(ProductDto.class))).thenReturn(productDto);

        mockMvc.perform(post("/products").content(objectMapper.writeValueAsString(productDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(productDto.name()))
                .andExpect(jsonPath("$.description").value(productDto.description()))
                .andExpect(jsonPath("$.imgUrl").value(productDto.imgUrl()))
                .andExpect(jsonPath("$.price").value(productDto.price()))
                .andExpect(jsonPath("$.categories[0].id").value(productDto.categories().stream().findFirst().get().id()))
                .andExpect(jsonPath("$.categories[1].id").value(productDto.categories().stream().skip(1).findFirst().get().id()));
    }

    @Test
    void updateProductById() throws Exception{
        when(productService.updateProduct(any(Long.class),any(ProductDto.class))).thenReturn(productDto);

        mockMvc.perform(put("/products/1").content(objectMapper.writeValueAsString(productDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(productDto.name()))
                .andExpect(jsonPath("$.description").value(productDto.description()))
                .andExpect(jsonPath("$.imgUrl").value(productDto.imgUrl()))
                .andExpect(jsonPath("$.price").value(productDto.price()))
                .andExpect(jsonPath("$.categories[0].id").value(productDto.categories().stream().findFirst().get().id()))
                .andExpect(jsonPath("$.categories[1].id").value(productDto.categories().stream().skip(1).findFirst().get().id()));
    }

    @Test
    void deleteProductById() throws Exception {

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());
    }
}