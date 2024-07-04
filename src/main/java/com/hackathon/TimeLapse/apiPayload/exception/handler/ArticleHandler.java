package com.hackathon.TimeLapse.apiPayload.exception.handler;

import com.hackathon.TimeLapse.apiPayload.code.BaseErrorCode;
import com.hackathon.TimeLapse.apiPayload.exception.GeneralException;

public class ArticleHandler extends GeneralException {

    public ArticleHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}