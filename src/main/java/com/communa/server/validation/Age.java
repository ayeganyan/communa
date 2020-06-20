package com.communa.server.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = AgeValidator.class)
@Target({ FIELD})
@Retention(RUNTIME)
public @interface Age {

    String message() default "Provided age is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int from() default 18;

    int to() default 35;
}
