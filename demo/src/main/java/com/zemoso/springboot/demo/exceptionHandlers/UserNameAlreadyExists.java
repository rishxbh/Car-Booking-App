package com.zemoso.springboot.demo.exceptionHandlers;

public class UserNameAlreadyExists extends RuntimeException{

    public UserNameAlreadyExists(String message){
        super(message);
    }

    public UserNameAlreadyExists(String message,Throwable cause){
        super(message, cause);
    }

    public UserNameAlreadyExists(Throwable cause) {
        super(cause);
    }

}
