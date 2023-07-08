package br.com.compassuol.pb.challenge.msproducts.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceExceptionHandlerTest {

    @Mock
    ResourceNotValidException resourceNotValidException;

    @Mock
    ResourceNotFoundException resourceNotFoundException;

    @Mock
    Exception exception;

    @Mock
    WebRequest webRequest;

    @InjectMocks
    ResourceExceptionHandler resourceExceptionHandler;

    @Test
    void handleResourceNotValidException() {
        when(resourceNotValidException.getMessage()).thenReturn("resource not valid");
        when(webRequest.getDescription(false)).thenReturn("description");

        ResponseEntity<ErrorResponse> responseEntity = resourceExceptionHandler.handleResourceNotValidException(resourceNotValidException, webRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("resource not valid", Objects.requireNonNull(responseEntity.getBody()).getMessage());
    }

    @Test
    void handleResourceNotFoundException() {
        when(resourceNotFoundException.getMessage()).thenReturn("resource not found");

        ResponseEntity<ErrorResponse> responseEntity = resourceExceptionHandler.handleResourceNotFoundException(resourceNotFoundException, webRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("resource not found", Objects.requireNonNull(responseEntity.getBody()).getMessage());
    }

    @Test
    void handleException() {
        when(exception.getMessage()).thenReturn("resource already exists.");

        ResponseEntity<ErrorResponse> responseEntity = resourceExceptionHandler.handleGlobalException(exception, webRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("resource already exists.", Objects.requireNonNull(responseEntity.getBody()).getMessage());
    }


}