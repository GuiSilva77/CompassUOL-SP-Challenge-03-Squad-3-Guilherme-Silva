package br.com.compassuol.pb.challenge.msnotification.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RabbitConfigTest {

    @Test
    void messageConverter() {
        assertNotNull(new RabbitConfig().messageConverter());
    }

    @Test
    void rabbitTemplate() {
        assertNotNull(new RabbitConfig().rabbitTemplate(null));
    }
}