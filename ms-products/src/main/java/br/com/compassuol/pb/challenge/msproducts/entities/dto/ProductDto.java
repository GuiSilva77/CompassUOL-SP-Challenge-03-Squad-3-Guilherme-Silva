package br.com.compassuol.pb.challenge.msproducts.entities.dto;

import br.com.compassuol.pb.challenge.msproducts.entities.Category;
import br.com.compassuol.pb.challenge.msproducts.entities.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link br.com.compassuol.pb.challenge.msproducts.entities.Product}
 */
public record ProductDto(@NotNull(message = "Date must not be null.") Date date,
                         @NotNull(message = "Description must not be null.") @Size(message = "Description lengh must be between 3 and 128 characters.", min = 3, max = 128) String description,
                         @NotNull(message = "Name must not be null.") @Size(message = "Name length must be between 3 and 50 characters.", min = 3, max = 50) String name,
                         @NotNull(message = "Image URL must not be null.") @Size(message = "Image URL length must be max 128 characters", max = 128) @URL(message = "Image URL must be a valid URL") String imgUrl,
                         @NotNull(message = "Product price must not be null.") @PositiveOrZero(message = "Product price must be positive or zero.") BigDecimal price,
                         Set<CategoryDto> categories) implements Serializable {
    public Product toProduct() {
        return new Product(null, date, description, name, imgUrl, price,
                categories.stream().map(categoryDto -> new Category(categoryDto.id())).collect(Collectors.toSet()));
    }
}