package br.com.compassuol.pb.challenge.msproducts.exceptions;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ErrorResponseTest {

    @Test
    void constructor() {
        ErrorResponse errorResponse = new ErrorResponse(null, null, null, null);
        assertNull(errorResponse.getTimestamp());
        assertNull(errorResponse.getStatus());
        assertNull(errorResponse.getMessage());
        assertNull(errorResponse.getDescription());
    }

    @Test
    void testGettersAndSetters() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        ErrorResponse errorResponse = new ErrorResponse(date, 404, "message", "description");

        assertEquals(date, errorResponse.getTimestamp());
        assertEquals(404, errorResponse.getStatus());
        assertEquals("message", errorResponse.getMessage());
        assertEquals("description", errorResponse.getDescription());

        ErrorResponse errorResponse2 = new ErrorResponse(date, 404, "message", "description");
        errorResponse2.setTimestamp(date);
        errorResponse2.setStatus(404);
        errorResponse2.setMessage("message");
        errorResponse2.setDescription("description");

        assertEquals(date, errorResponse2.getTimestamp());
        assertEquals(404, errorResponse2.getStatus());
        assertEquals("message", errorResponse2.getMessage());
        assertEquals("description", errorResponse2.getDescription());

    }
}