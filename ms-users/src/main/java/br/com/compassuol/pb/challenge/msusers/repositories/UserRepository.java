package br.com.compassuol.pb.challenge.msusers.repositories;

import br.com.compassuol.pb.challenge.msusers.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}