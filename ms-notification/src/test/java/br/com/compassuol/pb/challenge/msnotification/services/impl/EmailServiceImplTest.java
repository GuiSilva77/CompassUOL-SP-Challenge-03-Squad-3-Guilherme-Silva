package br.com.compassuol.pb.challenge.msnotification.services.impl;

import br.com.compassuol.pb.challenge.msnotification.entities.Email;
import br.com.compassuol.pb.challenge.msnotification.entities.enums.EmailStatus;
import br.com.compassuol.pb.challenge.msnotification.repositories.EmailRepository;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @Mock
    JavaMailSender mailSender;

    @Mock
    EmailRepository emailRepository;

    @InjectMocks
    EmailServiceImpl emailService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        reset(mailSender, emailRepository);
    }

    @Test
    void sendEmail() {
        Email email = new Email("email@email.com", "COMPANY", "Person", "person@email.com", "Subject", "body", "text/html");
        when(emailRepository.save(any(Email.class))).thenReturn(email);
        when(mailSender.createMimeMessage()).thenReturn(mock(MimeMessage.class));
        Email emailSaved = emailService.sendEmail(email);

        assertNotNull(emailSaved);
        assertEquals(EmailStatus.SENT, emailSaved.getStatus());
        verify(emailRepository, times(1)).save(any(Email.class));
        verify(mailSender, times(1)).send(any(MimeMessage.class));
    }

    @Test
    void sendEmailNotValid() {
        Email email = new Email("email@email.com", "COMPANY", "Person", "person@email.com", "Subject", "body", "text/html");
        when(emailRepository.save(any(Email.class))).thenReturn(email);
        when(mailSender.createMimeMessage()).thenThrow(new RuntimeException("Error sending email"));

        Email emailSaved = emailService.sendEmail(email);

        assertNotNull(emailSaved);
        assertEquals(EmailStatus.ERROR, emailSaved.getStatus());
        verify(emailRepository, times(1)).save(any(Email.class));
        verify(mailSender, times(0)).send(any(MimeMessage.class));
    }
}