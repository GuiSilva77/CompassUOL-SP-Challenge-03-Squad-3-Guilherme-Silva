package br.com.compassuol.pb.challenge.msproducts.services.impl;

import br.com.compassuol.pb.challenge.msproducts.entities.dto.EmailDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static br.com.compassuol.pb.challenge.msproducts.constants.EmailConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserNotificationImplTest {

    @Mock
    RabbitTemplate rabbitTemplate;

    @InjectMocks
    UserNotificationImpl userNotification;

    String EMAIL = "email@email.com";
    String NAME = "name";


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        reset(rabbitTemplate);
    }

    @Test
    void sendNewUserNotification() {
        userNotification.sendNewUserNotification(EMAIL, NAME);

        verify(rabbitTemplate).convertAndSend(eq("exchange"), eq("user_notif"), any(EmailDto.class));
    }

    @Test
    void sendUserModifiedNotification() {
        userNotification.sendUserModifiedNotification(EMAIL, NAME);

        verify(rabbitTemplate).convertAndSend(eq("exchange"), eq("user_notif"), any(EmailDto.class));
    }

    @Test
    void generateTemplate() {
        EmailDto emailDto = userNotification.generateTemplate(EMAIL, NAME, "NEW_USER");

        assertEquals(EMAIL_SENDER, emailDto.fromEmail());
        assertEquals(EMAIL_NAME, emailDto.fromName());
        assertEquals(NAME, emailDto.replyTo());
        assertEquals(EMAIL, emailDto.to());
        assertEquals(EMAIL_SUBJECT_NEW_USER, emailDto.subject());
        assertEquals(String.format(EMAIL_BODY_NEW_USER, NAME), emailDto.body());
        assertEquals(EMAIL_CONTENT_TYPE_HTML, emailDto.contentType());

    }
}