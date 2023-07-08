package br.com.compassuol.pb.challenge.msproducts.controllers;

import br.com.compassuol.pb.challenge.msproducts.entities.dto.ProductDto;
import br.com.compassuol.pb.challenge.msproducts.entities.payload.ProductPayload;
import br.com.compassuol.pb.challenge.msproducts.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<ProductPayload> getProducts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                      @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                      @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir) {

        ProductPayload payload = productService.findAll(pageNo, pageSize, sortBy, sortDir);

        return ResponseEntity.ok(payload);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") Long id) {

        ProductDto product = productService.findProductById(id);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProductById(@Valid @RequestBody ProductDto request) {

        ProductDto product = productService.addProduct(request);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable(name = "id") Long id, @Valid @RequestBody ProductDto request) {

        ProductDto product = productService.updateProduct(id, request);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable(name = "id") Long id) {

        productService.deleteProduct(id);

        return "Product deleted successfully";
    }


}

