package br.com.compassuol.pb.challenge.msproducts.repositories;

import br.com.compassuol.pb.challenge.msproducts.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}