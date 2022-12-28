package proiect.ProiectBiblioteca.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyNumericLettersValidation implements ConstraintValidator<OnlyLetters, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null)
            return false;
        return value.matches("^[0-9 ]*$");
    }
}
