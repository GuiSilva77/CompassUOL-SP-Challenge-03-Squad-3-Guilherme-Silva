package br.com.compassuol.pb.challenge.msproducts.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entity, String field, String fieldValue) {
        super(String.format("%s not found with %s: '%s'", entity, field, fieldValue));
    }
}
