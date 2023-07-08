package br.com.compassuol.pb.challenge.msnotification.consumers;

import br.com.compassuol.pb.challenge.msnotification.entities.EmailTemplate;
import br.com.compassuol.pb.challenge.msnotification.entities.dto.NotificationDto;
import br.com.compassuol.pb.challenge.msnotification.repositories.EmailTemplateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static br.com.compassuol.pb.challenge.msnotification.constants.EmailConstants.*;

@ExtendWith(MockitoExtension.class)
class UserNotificationConsumerTest {

    @Mock
    EmailTemplateRepository emailTemplateRepository;

    @Mock
    RabbitTemplate rabbitTemplate;

    @InjectMocks
    UserNotificationConsumer userNotificationConsumer;

    @Test
    void receiveMessageNewUser() {
        NotificationDto notificationDto = new NotificationDto("nome sobrenome", "email@email.com", "NEW_USER");
        EmailTemplate emailTemplate = new EmailTemplate(EMAIL_SENDER, EMAIL_NAME, "nome sobrenome", "email@email.com", EMAIL_SUBJECT_NEW_USER, String.format(EMAIL_BODY_NEW_USER, "nome sobrenome"), EMAIL_CONTENT_TYPE_HTML);
        when(emailTemplateRepository.save(any(EmailTemplate.class))).thenReturn(emailTemplate);

        userNotificationConsumer.receiveMessage(notificationDto);

        verify(emailTemplateRepository).save(any(EmailTemplate.class));
        verify(rabbitTemplate).convertAndSend("exchange", "email_send", emailTemplate.toDto());
    }
}