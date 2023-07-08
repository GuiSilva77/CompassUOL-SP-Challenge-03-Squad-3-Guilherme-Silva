package br.com.compassuol.pb.challenge.msproducts.entities.payload;

import br.com.compassuol.pb.challenge.msproducts.entities.dto.ProductDto;

import java.util.List;

public record ProductPayload (
    List<ProductDto> products,
    int pageNo,
    int pageSize,
    int totalElements,
    int totalPages,
    boolean last) {
}
