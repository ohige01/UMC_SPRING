package com.example.demo.apiPayload.exception.handler;

import com.example.demo.apiPayload.code.BaseErrorCode;
import com.example.demo.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
