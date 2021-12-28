package com.devsuperior.projetoCapitulo1.services.exceptions;

public class ResouceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResouceNotFoundException(String message) {
        super(message);
    }
}
