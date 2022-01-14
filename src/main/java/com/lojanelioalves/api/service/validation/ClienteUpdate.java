package com.lojanelioalves.api.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ClienteUpdateValidator.class)
//@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface ClienteUpdate {
    String message() default "Erro de validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
