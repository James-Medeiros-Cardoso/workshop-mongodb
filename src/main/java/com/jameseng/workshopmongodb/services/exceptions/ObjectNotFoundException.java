package com.jameseng.workshopmongodb.services.exceptions;

public class ObjectNotFoundException extends RuntimeException { // RuntimeException = não exige ser tratada
    // se fosse: public class ObjectNotFoundException extends Exception = deve obrigatoriamente tratar

    public ObjectNotFoundException(String message) {
        super(message);
    }
}