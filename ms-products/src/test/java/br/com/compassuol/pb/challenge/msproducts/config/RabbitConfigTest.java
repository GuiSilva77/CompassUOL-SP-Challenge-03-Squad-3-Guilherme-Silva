package br.com.compassuol.pb.challenge.msproducts.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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