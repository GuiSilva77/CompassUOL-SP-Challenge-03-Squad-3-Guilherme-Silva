package br.com.compassuol.pb.challenge.msauth.repositories;

import br.com.compassuol.pb.challenge.msauth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}