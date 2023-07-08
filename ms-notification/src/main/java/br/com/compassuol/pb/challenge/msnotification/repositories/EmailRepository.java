package br.com.compassuol.pb.challenge.msnotification.repositories;

import br.com.compassuol.pb.challenge.msnotification.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
}