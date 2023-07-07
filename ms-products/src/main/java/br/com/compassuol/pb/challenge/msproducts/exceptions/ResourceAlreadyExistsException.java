package br.com.compassuol.pb.challenge.msproducts.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String entity) {
        super(String.format("%s already exists", entity));
    }
}
