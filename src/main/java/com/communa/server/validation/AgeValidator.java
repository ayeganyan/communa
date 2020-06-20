package com.communa.server.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AgeValidator implements ConstraintValidator<Age, Date> {

    private int from;

    private int to;

    @Override
    public void initialize(Age constraintAnnotation) {
        from = constraintAnnotation.from();
        to = constraintAnnotation.to();
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        try{
            Date now = Calendar.getInstance().getTime();
            long diffInMillies = Math.abs(now.getTime() - date.getTime());
            long age = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;

            return  age > from &&
                    age < to;

        } catch (Exception ex) {
            return false;
        }
    }
}
