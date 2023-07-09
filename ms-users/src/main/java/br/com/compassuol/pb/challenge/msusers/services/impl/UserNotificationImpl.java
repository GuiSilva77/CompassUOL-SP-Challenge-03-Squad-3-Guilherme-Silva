package br.com.compassuol.pb.challenge.msusers.services.impl;

import br.com.compassuol.pb.challenge.msusers.entities.dto.EmailDto;
import br.com.compassuol.pb.challenge.msusers.entities.enums.EmailType;
import br.com.compassuol.pb.challenge.msusers.services.UserNotificationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import static br.com.compassuol.pb.challenge.msusers.constants.EmailConstants.*;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Service
public class UserNotificationImpl implements UserNotificationService {

    private final RabbitTemplate rabbitTemplate;

    public UserNotificationImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendNewUserNotification(String email, String name) {
            EmailDto emailDto = generateTemplate(email, name, "NEW_USER");

            rabbitTemplate.convertAndSend("exchange", "user_notif", emailDto);
    }

    @Override
    public void sendUserModifiedNotification(String email, String name) {
        EmailDto emailDto = generateTemplate(email, name, "USER_MODIFIED");

        rabbitTemplate.convertAndSend("exchange", "user_notif", emailDto);
    }

    public EmailDto generateTemplate(String email, String name, String type) {

        return new EmailDto(
                EMAIL_SENDER, EMAIL_NAME, name, email,
                EmailType.valueOf(type).equals(EmailType.NEW_USER) ?
                        EMAIL_SUBJECT_NEW_USER :
                        EMAIL_SUBJECT_USER_MODIFIED,
                String.format(
                        EmailType.valueOf(type).equals(EmailType.NEW_USER) ?
                                EMAIL_BODY_NEW_USER :
                                EMAIL_BODY_USER_MODIFIED,
                        name), EMAIL_CONTENT_TYPE_HTML);
    }
}
