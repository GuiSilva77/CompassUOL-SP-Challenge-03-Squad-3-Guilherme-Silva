package br.com.compassuol.pb.challenge.msproducts.services;

import br.com.compassuol.pb.challenge.msproducts.entities.dto.ProductDto;
import br.com.compassuol.pb.challenge.msproducts.entities.payload.ProductPayload;

public interface ProductService {
    ProductDto addProduct(ProductDto request);

    ProductDto findProductById(Long id);

    ProductPayload findAll(int pageNo, int pageSize, String sortBy,String sortDir);

    void deleteProduct(Long id);

    ProductDto updateProduct(Long id, ProductDto request);
}
