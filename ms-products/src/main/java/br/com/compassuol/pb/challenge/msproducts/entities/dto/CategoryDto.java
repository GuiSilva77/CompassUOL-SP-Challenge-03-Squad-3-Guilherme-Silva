package br.com.compassuol.pb.challenge.msproducts.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link br.com.compassuol.pb.challenge.msproducts.entities.Category}
 */
public record CategoryDto(
        @NotNull(message = "Category id must not be null.") @Positive(message = "Category id value must be higher than zero") Long id) implements Serializable {
}