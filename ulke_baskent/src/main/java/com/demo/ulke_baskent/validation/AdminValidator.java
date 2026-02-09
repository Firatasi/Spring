package com.demo.ulke_baskent.validation;

import com.demo.ulke_baskent.anotasyon.NotAdmin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdminValidator implements ConstraintValidator<NotAdmin, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return !value.equals("admin");
    }
}
