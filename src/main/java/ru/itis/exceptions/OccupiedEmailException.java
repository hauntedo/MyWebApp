package ru.itis.exceptions;

public class OccupiedEmailException extends RuntimeException{

    public OccupiedEmailException(){};

    public OccupiedEmailException(String message) {
        super(message);
    }
}
