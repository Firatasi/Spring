package com.demo.ulke_baskent.anotasyon;

import com.demo.ulke_baskent.validation.SifreValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {SifreValidator.class})
@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface Sifre {

    String message() default "{Şifreler eşleşmelidir!}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
