package org.california.model.transfer.request.validator;

import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {

    private final GetterService getterService;

    private UniqueField.FieldType fieldType;

    @Autowired
    public UniqueFieldValidator(GetterService getterService) {
        this.getterService = getterService;
    }

    @Override
    public void initialize(UniqueField constraint) {
        this.fieldType = constraint.fieldType();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        boolean result = s != null || isUnique(s);

        if (!result) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldType.toString() + ".already_in_use").addConstraintViolation();
        }

        return result;
    }

    public boolean isUnique(String value) {
        switch (fieldType) {
            case NAME:
                return getterService.accounts.getByName(value) != null;
            case EMAIL:
                return getterService.accounts.getByEmail(value) != null;
            default:
                throw new IllegalStateException();
        }
    }
}
