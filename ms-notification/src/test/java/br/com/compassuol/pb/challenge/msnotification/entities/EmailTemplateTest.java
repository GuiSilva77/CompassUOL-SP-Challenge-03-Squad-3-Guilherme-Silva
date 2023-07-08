package br.com.compassuol.pb.challenge.msnotification.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTemplateTest {

    @Test
    void gettersAndSetters() {
        EmailTemplate email = new EmailTemplate();
        email.setId(1L);
        email.setFromEmail("email@email.com");
        email.setFromName("name");
        email.setReplyTo("nome");
        email.setTo("email@email.com");
        email.setSubject("subject");
        email.setBody("body");
        email.setContentType("text/html");

        assertEquals(1L, email.getId());
        assertEquals("email@email.com", email.getFromEmail());
        assertEquals("name", email.getFromName());
        assertEquals("nome", email.getReplyTo());
        assertEquals("email@email.com", email.getTo());
        assertEquals("subject", email.getSubject());
        assertEquals("body", email.getBody());
        assertEquals("text/html", email.getContentType());
    }
}