package ru.itis.exceptions;

public class NoSuchLoginException extends RuntimeException {

    public NoSuchLoginException(){
    }

    public NoSuchLoginException(String message) {
        super(message);
    }
}
