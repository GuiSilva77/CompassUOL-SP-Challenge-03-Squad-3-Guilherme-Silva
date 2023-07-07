package br.com.compassuol.pb.challenge.msproducts.services.impl;

import br.com.compassuol.pb.challenge.msproducts.entities.dto.NotificationDto;
import br.com.compassuol.pb.challenge.msproducts.services.UserNotificationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationImpl implements UserNotificationService {

    private final RabbitTemplate rabbitTemplate;

    public UserNotificationImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendNewUserNotification(String email, String name) {
            NotificationDto notificationDto = new NotificationDto(name, email, "NEW_USER");

            rabbitTemplate.convertAndSend("exchange", "user_notif", notificationDto);
    }

    @Override
    public void sendUserModifiedNotification(String email, String name) {
        NotificationDto notificationDto = new NotificationDto(name, email, "USER_MODIFIED");

        rabbitTemplate.convertAndSend("exchange", "user_notif", notificationDto);
    }
}
