package com.packtpub.productservices.internal.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

    private String message;
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

}
