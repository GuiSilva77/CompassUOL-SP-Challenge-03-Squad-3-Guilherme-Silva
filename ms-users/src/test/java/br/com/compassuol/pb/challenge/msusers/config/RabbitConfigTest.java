package br.com.compassuol.pb.challenge.msusers.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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