package br.com.compassuol.pb.challenge.msnotification.repositories;

import br.com.compassuol.pb.challenge.msnotification.entities.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
}