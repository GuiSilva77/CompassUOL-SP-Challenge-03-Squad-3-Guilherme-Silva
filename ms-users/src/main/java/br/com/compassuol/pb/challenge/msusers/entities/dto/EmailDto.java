package br.com.compassuol.pb.challenge.msusers.entities.dto;

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
}
