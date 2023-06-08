package tatar.mackshchim.sm.validation.validators;

import tatar.mackshchim.sm.validation.constraints.ValidState;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StateValidator implements ConstraintValidator<ValidState, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            if (value.equals("POSTED") || value.equals("DELETED")) {
                return true;
            }
        }
        return false;
    }
}
