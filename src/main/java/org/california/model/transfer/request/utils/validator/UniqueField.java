package org.california.model.transfer.request.utils.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueFieldValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniqueField {

    String message() default "value.already_in_use";

    FieldType fieldType();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    enum FieldType {
        EMAIL("email"), NAME("name");

        private final String fieldType;

        FieldType(String type) {
            this.fieldType = type;
        }

        @Override
        public String toString() {
            return this.fieldType;
        }
    }

}