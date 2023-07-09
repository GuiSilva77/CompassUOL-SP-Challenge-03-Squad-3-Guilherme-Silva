package br.com.compassuol.pb.challenge.msproducts.services.impl;

import br.com.compassuol.pb.challenge.msproducts.entities.Category;
import br.com.compassuol.pb.challenge.msproducts.entities.Product;
import br.com.compassuol.pb.challenge.msproducts.entities.dto.ProductDto;
import br.com.compassuol.pb.challenge.msproducts.entities.payload.ProductPayload;
import br.com.compassuol.pb.challenge.msproducts.exceptions.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.msproducts.repositories.ProductRepository;
import br.com.compassuol.pb.challenge.msproducts.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto addProduct(ProductDto request) {
        Product product = request.toProduct();

        return productRepository.save(product).toDto();
    }

    @Override
    public ProductDto findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id.toString())
        );

        return product.toDto();
    }

    @Override
    public ProductPayload findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Page<Product> page = productRepository.findAll(
                PageRequest.of(pageNo,
                        pageSize,
                        Sort.by(Sort.Direction.valueOf(sortDir), sortBy)
                )
        );

        List<ProductDto> products = page.getContent().stream()
                .map(Product::toDto)
                .toList();

        return new ProductPayload(
                products,
                page.getNumber(),
                page.getSize(),
                page.getNumberOfElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id))
            throw new ResourceNotFoundException("Product", "id", id.toString());

        productRepository.deleteById(id);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto request) {
        Product updateProduct = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id.toString())
        );

        updateProduct.setDate(request.date());
        updateProduct.setDescription(request.description());
        updateProduct.setName(request.name());
        updateProduct.setImgUrl(request.imgUrl());
        updateProduct.setPrice(request.price());
        updateProduct.setCategories(request.categories().stream().map(category -> new Category(category.id())).collect(Collectors.toSet()));

        return productRepository.save(updateProduct).toDto();
    }
}
