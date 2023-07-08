package br.com.compassuol.pb.challenge.msnotification.entities;

import br.com.compassuol.pb.challenge.msnotification.entities.dto.EmailDto;
import br.com.compassuol.pb.challenge.msnotification.entities.enums.EmailStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void gettersAndSetters() {
        Email email = new Email();
        email.setId(1L);
        email.setFromEmail("email@email.com");
        email.setFromName("name");
        email.setReplyTo("nome");
        email.setTo("email@email.com");
        email.setSubject("subject");
        email.setBody("body");
        email.setContentType("text/html");
        email.setStatus(EmailStatus.PENDING);
        email.setSendDate(null);

        assertEquals(1L, email.getId());
        assertEquals("email@email.com", email.getFromEmail());
        assertEquals("name", email.getFromName());
        assertEquals("nome", email.getReplyTo());
        assertEquals("email@email.com", email.getTo());
        assertEquals("subject", email.getSubject());
        assertEquals("body", email.getBody());
        assertEquals("text/html", email.getContentType());
        assertEquals(EmailStatus.PENDING, email.getStatus());
        assertNull(email.getSendDate());
    }

    @Test
    void testAllArgsConstructor() {
        Email email = new Email("fromEmail", "fromName", "replyTo", "to", "subject", "body", "contentType");

        assertEquals("fromEmail", email.getFromEmail());
        assertEquals("fromName", email.getFromName());
        assertEquals("replyTo", email.getReplyTo());
        assertEquals("to", email.getTo());
        assertEquals("subject", email.getSubject());
        assertEquals("body", email.getBody());
        assertEquals("contentType", email.getContentType());
    }

    @Test
    void toDto() {
        Email email = new Email("fromEmail", "fromName", "replyTo", "to", "subject", "body", "contentType");
        email.setId(1L);
        email.setStatus(EmailStatus.PENDING);
        email.setSendDate(null);

        EmailDto emailDto = email.toDto();

        assertEquals("fromEmail", emailDto.fromEmail());
        assertEquals("fromName", emailDto.fromName());
        assertEquals("replyTo", emailDto.replyTo());
        assertEquals("to", emailDto.to());
        assertEquals("subject", emailDto.subject());
        assertEquals("body", emailDto.body());
        assertEquals("contentType", emailDto.contentType());
    }
}