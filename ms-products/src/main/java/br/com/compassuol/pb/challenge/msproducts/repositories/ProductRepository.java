package br.com.compassuol.pb.challenge.msproducts.repositories;

import br.com.compassuol.pb.challenge.msproducts.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}