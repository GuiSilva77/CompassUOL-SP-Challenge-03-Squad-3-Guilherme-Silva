package br.com.compassuol.pb.challenge.msauth.repositories;

import br.com.compassuol.pb.challenge.msauth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}