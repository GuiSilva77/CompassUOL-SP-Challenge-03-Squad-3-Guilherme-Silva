package br.com.compassuol.pb.challenge.msnotification.consumers;

import br.com.compassuol.pb.challenge.msnotification.entities.EmailTemplate;
import br.com.compassuol.pb.challenge.msnotification.entities.dto.EmailDto;
import br.com.compassuol.pb.challenge.msnotification.entities.dto.NotificationDto;
import br.com.compassuol.pb.challenge.msnotification.entities.enums.EmailType;
import br.com.compassuol.pb.challenge.msnotification.repositories.EmailTemplateRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static br.com.compassuol.pb.challenge.msnotification.constants.EmailConstants.*;

@Service
public class UserNotificationConsumer {

    private final EmailTemplateRepository emailTemplateRepository;
    private final RabbitTemplate rabbitTemplate;

    public UserNotificationConsumer(EmailTemplateRepository emailTemplateRepository, RabbitTemplate rabbitTemplate) {
        this.emailTemplateRepository = emailTemplateRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "user_notif")
    public void receiveMessage(@Payload NotificationDto message) {

        EmailTemplate emailTemplate = new EmailTemplate();

        emailTemplate.setSubject(EmailType.valueOf(message.type()).equals(EmailType.NEW_USER) ?
                EMAIL_SUBJECT_NEW_USER :
                EMAIL_SUBJECT_USER_MODIFIED);
        emailTemplate.setFromName(EMAIL_NAME);
        emailTemplate.setFromEmail(EMAIL_SENDER);
        emailTemplate.setReplyTo(message.replyTo());
        emailTemplate.setTo(message.to());
        emailTemplate.setContentType(EMAIL_CONTENT_TYPE_HTML);
        emailTemplate.setBody(String.format(
                 EmailType.valueOf(message.type()).equals(EmailType.NEW_USER) ?
                        EMAIL_BODY_NEW_USER :
                        EMAIL_BODY_USER_MODIFIED,
                message.replyTo()
        ));

        emailTemplateRepository.save(emailTemplate);

        EmailDto email = emailTemplate.toDto();

        rabbitTemplate.convertAndSend("exchange", "email_send", email);
    }
}
