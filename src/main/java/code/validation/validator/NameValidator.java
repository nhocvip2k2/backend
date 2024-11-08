package code.validation.validator;


import code.validation.anotation.ValidName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

  @Override
  public void initialize(ValidName constraintAnnotation) {
  }

  @Override
  public boolean isValid(String name, ConstraintValidatorContext context) {
    return name != null && name.matches("[a-zA-Z ]+") && name.length() >= 3;
  }
}
