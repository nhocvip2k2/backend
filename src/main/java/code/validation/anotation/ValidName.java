package code.validation.anotation;

import code.validation.validator.NameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
  String message() default "Tên không hợp lệ";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}

