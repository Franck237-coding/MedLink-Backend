package com.example.medapp.exception;

public class ResourceExistException extends RuntimeException {
    public ResourceExistException(String message) {
        super(message);
    }
}
