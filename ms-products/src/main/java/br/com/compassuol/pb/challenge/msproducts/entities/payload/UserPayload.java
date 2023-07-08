package br.com.compassuol.pb.challenge.msproducts.entities.payload;

import br.com.compassuol.pb.challenge.msproducts.entities.Role;
import br.com.compassuol.pb.challenge.msproducts.entities.User;
import br.com.compassuol.pb.challenge.msproducts.entities.dto.RoleDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.stream.Collectors;

public record UserPayload(
        @NotNull(message = "First name must not be null.") @Size(message = "First name must be max 30 characters.", max = 30) String firstName,
        @NotNull(message = "Last name must not be null.") @Size(message = "Last name must be max 30 characters.", max = 30) String lastName,
        @NotNull(message = "Email must not be null.") @Size(message = "Email must be max 50 characters.", max = 50) String email,
        @NotNull(message = "Password must not be null.") @Size(message = "Password must be between 8 and 64 characters", min = 8, max = 64) String password,
        Set<RoleDto> roles){

    public User toUser() {
        return new User(this.firstName(), this.lastName(), this.email(), this.password(),
                this.roles().stream()
                        .map(role -> new Role(role.id()))
                        .collect(Collectors.toSet())
        );
    }
}
