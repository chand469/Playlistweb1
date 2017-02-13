package com.msse.week2.SpringBootJPA2.model

import javax.validation.Constraint
import javax.validation.Payload
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Constraint(validatedBy = ValidatePassword)
@Target([ElementType.METHOD,ElementType.FIELD])
@Retention(RetentionPolicy.RUNTIME)

@interface SecurePassword {
    String message() default "Password does not meet complexity requirements"
    Class<?>[] groups() default []
    Class<? extends Payload>[] payload() default []

}