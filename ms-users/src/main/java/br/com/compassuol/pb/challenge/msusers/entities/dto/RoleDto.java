package br.com.compassuol.pb.challenge.msusers.entities.dto;

import br.com.compassuol.pb.challenge.msusers.entities.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link Role}
 */
public record RoleDto(
        @NotNull(message = "Role id must not be null.") @Positive(message = "Role must be higher than zero.") Long id) implements Serializable {
}