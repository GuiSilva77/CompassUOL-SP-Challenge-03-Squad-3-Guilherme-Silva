package br.com.compassuol.pb.challenge.msnotification.entities.dto;

import java.io.Serializable;

public record NotificationDto(
        String replyTo,
        String to,
        String type
        ) implements Serializable {
}
