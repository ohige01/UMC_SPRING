package com.example.demo.apiPayload.exception.handler;

import com.example.demo.apiPayload.code.BaseErrorCode;
import com.example.demo.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
