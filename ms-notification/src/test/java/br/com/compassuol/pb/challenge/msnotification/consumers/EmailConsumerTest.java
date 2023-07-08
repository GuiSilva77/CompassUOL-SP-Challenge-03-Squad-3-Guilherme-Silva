package br.com.compassuol.pb.challenge.msnotification.consumers;

import br.com.compassuol.pb.challenge.msnotification.entities.Email;
import br.com.compassuol.pb.challenge.msnotification.entities.dto.EmailDto;
import br.com.compassuol.pb.challenge.msnotification.services.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailConsumerTest {

    @Mock
    EmailService emailService;

    @InjectMocks
    EmailConsumer emailConsumer;

    @Test
    void receiveMessage() {
        EmailDto message = new EmailDto("fromEmail", "fromName", "replyTo", "to", "subject", "body", "contentType");

        emailConsumer.receiveMessage(message);

        verify(emailService).sendEmail(any(Email.class));
    }
}