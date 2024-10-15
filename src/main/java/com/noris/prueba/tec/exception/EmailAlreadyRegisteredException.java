package com.noris.prueba.tec.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {

        public EmailAlreadyRegisteredException(String message) {
            super(message);
        }
}
