package proiect.ProiectBiblioteca.validator;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {OnlyLettersValidation.class})
public @interface OnlyLetters {

    String message() default "Only letter required";

    //represents group of constraints
    Class<?>[] groups() default {};

    //represents additional information about annotation
    Class<? extends Payload>[] payload() default {};
}
