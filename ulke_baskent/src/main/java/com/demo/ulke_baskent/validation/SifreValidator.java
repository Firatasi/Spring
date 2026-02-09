package com.demo.ulke_baskent.validation;

import com.demo.ulke_baskent.anotasyon.Sifre;
import com.demo.ulke_baskent.entity.Ulke;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SifreValidator implements ConstraintValidator<Sifre, Ulke> {
    @Override
    public boolean isValid(Ulke value, ConstraintValidatorContext context) {
        return false;
    }
}
