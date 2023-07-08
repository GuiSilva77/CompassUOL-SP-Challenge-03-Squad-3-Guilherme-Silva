package br.com.compassuol.pb.challenge.msproducts.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link br.com.compassuol.pb.challenge.msproducts.entities.User}
 */
public record UserDto(
        @NotNull(message = "First name must not be null.") @Size(message = "First name must be max 30 characters.", max = 30) String firstName,
        @NotNull(message = "Last name must not be null.") @Size(message = "Last name must be max 30 characters.", max = 30) String lastName,
        @NotNull(message = "Email must not be null.") @Size(message = "Email must be max 50 characters.", max = 50) String email,
        Set<RoleDto> roles) implements Serializable {

}