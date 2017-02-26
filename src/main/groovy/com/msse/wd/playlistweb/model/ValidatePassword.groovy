package com.msse.wd.playlistweb.model

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class ValidatePassword implements ConstraintValidator<SecurePassword,String> {

    @Override
    void initialize(SecurePassword constraintAnnotation){
    }

     @Override
      boolean isValid(String value,ConstraintValidatorContext cxt){
      def pwRegEx = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$/
         return !value || value.matches(pwRegEx)
      }
}