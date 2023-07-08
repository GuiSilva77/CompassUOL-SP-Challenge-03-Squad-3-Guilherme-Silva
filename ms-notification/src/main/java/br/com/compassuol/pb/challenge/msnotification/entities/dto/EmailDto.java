package br.com.compassuol.pb.challenge.msnotification.entities.dto;

import br.com.compassuol.pb.challenge.msnotification.entities.Email;

import java.io.Serializable;

public record EmailDto(
        String fromEmail,
        String fromName,
        String replyTo,
        String to,
        String subject,
        String body,
        String contentType
    ) implements Serializable {

    public Email toEmail() {
            return new Email(fromEmail, fromName, replyTo, to, subject, body, contentType);
    }
}
