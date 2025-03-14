package me.dio.primeira.service.exception;

public class LivroAlreadyExistsException extends RuntimeException {
    public LivroAlreadyExistsException(String message) {
        super(message);
    }
}