package br.com.compassuol.pb.challenge.msnotification.entities.dto;

import br.com.compassuol.pb.challenge.msnotification.entities.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailDtoTest {

    @Test
    void toEmail() {
        EmailDto emailDto = new EmailDto("fromEmail", "fromName", "replyTo", "to", "subject", "body", "contentType");

        Email email = emailDto.toEmail();

        assertEquals("fromEmail", email.getFromEmail());
        assertEquals("fromName", email.getFromName());
        assertEquals("replyTo", email.getReplyTo());
        assertEquals("to", email.getTo());
        assertEquals("subject", email.getSubject());
        assertEquals("body", email.getBody());
        assertEquals("contentType", email.getContentType());
    }
}