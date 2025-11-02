package com.campushub.exceptions;

public class HubUserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1;

    public HubUserNotFoundException(String msg){
        super(msg);
    }

}
