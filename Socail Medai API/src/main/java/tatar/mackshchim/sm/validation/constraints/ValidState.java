package tatar.mackshchim.sm.validation.constraints;

import tatar.mackshchim.sm.validation.validators.StateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StateValidator.class)
public @interface ValidState {

    String message() default "{invalidState}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
