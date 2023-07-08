package br.com.compassuol.pb.challenge.msnotification.services.impl;

import br.com.compassuol.pb.challenge.msnotification.entities.Email;
import br.com.compassuol.pb.challenge.msnotification.entities.enums.EmailStatus;
import br.com.compassuol.pb.challenge.msnotification.repositories.EmailRepository;
import br.com.compassuol.pb.challenge.msnotification.services.EmailService;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    @Override
    public Email sendEmail(Email email) {
        Email emailSaved;

        email.setSendDate(LocalDateTime.now());
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setFrom(email.getFromEmail());
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email.getTo()));
            message.setSubject(email.getSubject());
            message.setContent(email.getBody(), email.getContentType());

            javaMailSender.send(message);

            email.setStatus(EmailStatus.SENT);
        } catch (Exception e) {
            email.setStatus(EmailStatus.ERROR);
        } finally {
            emailSaved = emailRepository.save(email);
        }

        return emailSaved;
    }
}
