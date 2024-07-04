package com.hackathon.TimeLapse.apiPayload.exception;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String message){
        super(message);
    }
}
