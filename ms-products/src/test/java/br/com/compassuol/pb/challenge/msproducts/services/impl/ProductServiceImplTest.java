package br.com.compassuol.pb.challenge.msproducts.services.impl;

import br.com.compassuol.pb.challenge.msproducts.entities.Category;
import br.com.compassuol.pb.challenge.msproducts.entities.Product;
import br.com.compassuol.pb.challenge.msproducts.entities.dto.CategoryDto;
import br.com.compassuol.pb.challenge.msproducts.entities.dto.ProductDto;
import br.com.compassuol.pb.challenge.msproducts.entities.payload.ProductPayload;
import br.com.compassuol.pb.challenge.msproducts.exceptions.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.msproducts.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    Product product;

    ProductDto productDto;


    @BeforeEach
    void setUp() {
        product = new Product(new Date(), "Description", "Product 1", "https://url.com", BigDecimal.valueOf(156), Set.of(new Category(1L, "Category 1"), new Category(2L, "Category 2")));
        productDto = new ProductDto(new Date(), "Description", "Product 1", "https://url.com", BigDecimal.valueOf(156), Set.of(new CategoryDto(1L), new CategoryDto(2L)));
    }

    @AfterEach
    void tearDown() {
        reset(productRepository);
    }

    @Test
    void addProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto productDto = productService.addProduct(this.productDto);

        assertEquals(productDto, this.productDto);
    }

    @Test
    void findProductById() {
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));

        ProductDto productDto = productService.findProductById(1L);

        assertEquals(productDto.name(), this.productDto.name());
    }

    @Test
    void findProductByIdNotFound() {
        when(productRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.findProductById(1L));
    }

    @Test
    void findAll() {
        when(productRepository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        ProductPayload payload = productService.findAll(0, 1, "id", "ASC");

        assertEquals(payload.products(), List.of());
    }

    @Test
    void deleteProduct() {
        when(productRepository.existsById(any(Long.class))).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(any(Long.class));
    }

    @Test
    void deleteProductNotFound() {
        when(productRepository.existsById(any(Long.class))).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(1L));
    }

    @Test
    void updateProduct() {
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto productDto = productService.updateProduct(1L, this.productDto);

        assertEquals(productDto, this.productDto);
    }

    @Test
    void updateProductNotFound() {
        when(productRepository.existsById(any(Long.class))).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(1L, this.productDto));
    }
}