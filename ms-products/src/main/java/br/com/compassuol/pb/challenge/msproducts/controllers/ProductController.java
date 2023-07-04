package br.com.compassuol.pb.challenge.msproducts.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getProducts() {
        return "Products";
    }

    @GetMapping("{id}")
    public String getProductById(@PathVariable(name = "id") String id) {
        return "Product";
    }

    @PostMapping
    public String createProductById(@PathVariable(name = "id") String id) {
        return "Product";
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable(name = "id") String id) {
        return "Product";
    }

    @PutMapping("{id}")
    public String updateProductById(@PathVariable(name = "id") String id) {
        return "Product";
    }
}

