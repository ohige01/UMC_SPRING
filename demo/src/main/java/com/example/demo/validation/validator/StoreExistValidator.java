package com.example.demo.validation.validator;

import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.apiPayload.exception.handler.StoreHandler;
import com.example.demo.service.StoreService.StoreCommandService;
import com.example.demo.service.StoreService.StoreQueryService;
import com.example.demo.validation.annotation.ExistStore;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
/*
        Optional<Store> target = storeQueryService.findStore(value);

        if (target.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
*/
        try{
            storeCommandService.getStore(value);
        }catch (StoreHandler e){

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
