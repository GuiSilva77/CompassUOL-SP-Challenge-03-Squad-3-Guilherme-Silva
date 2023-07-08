package br.com.compassuol.pb.challenge.msnotification.consumers;

import br.com.compassuol.pb.challenge.msnotification.entities.Email;
import br.com.compassuol.pb.challenge.msnotification.entities.dto.EmailDto;
import br.com.compassuol.pb.challenge.msnotification.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }


    @RabbitListener(queues = "user_notif")
    public void receiveMessage(@Payload EmailDto message) {
        Email email = message.toEmail();

        emailService.sendEmail(email);
    }
}
