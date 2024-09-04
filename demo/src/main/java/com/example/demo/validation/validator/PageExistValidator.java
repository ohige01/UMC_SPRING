package com.example.demo.validation.validator;

import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.validation.annotation.ExistPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageExistValidator implements ConstraintValidator<ExistPage, Integer> {
    @Override
    public void initialize(ExistPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value > 0)
            return true;
        else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NOT_VALID.toString()).addConstraintViolation();
            return false;
        }
    }
}
