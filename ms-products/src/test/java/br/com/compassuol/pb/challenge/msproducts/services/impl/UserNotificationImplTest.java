package br.com.compassuol.pb.challenge.msproducts.services.impl;

import br.com.compassuol.pb.challenge.msproducts.entities.dto.NotificationDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserNotificationImplTest {

    @Mock
    RabbitTemplate rabbitTemplate;

    @InjectMocks
    UserNotificationImpl userNotification;

    @AfterEach
    void tearDown() {
        reset(rabbitTemplate);
    }

    @Test
    void sendNewUserNotification() {
        userNotification.sendNewUserNotification("email", "name");

        verify(rabbitTemplate).convertAndSend("exchange", "user_notif", new NotificationDto("name", "email", "NEW_USER"));
    }

    @Test
    void sendUserModifiedNotification() {
        userNotification.sendUserModifiedNotification("email", "name");

        verify(rabbitTemplate).convertAndSend("exchange", "user_notif", new NotificationDto("name", "email", "USER_MODIFIED"));
    }
}