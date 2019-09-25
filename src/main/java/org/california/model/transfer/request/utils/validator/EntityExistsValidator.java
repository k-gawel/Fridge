package org.california.model.transfer.request.utils.validator;

import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EntityExistsValidator implements ConstraintValidator<EntityExists, Number> {

    private final GetterService getterService;
    private EntityExists.EntityType entityType;

    @Autowired
    public EntityExistsValidator(GetterService getterService) {
        this.getterService = getterService;
    }

    public void initialize(EntityExists constraint) {
        this.entityType = constraint.entityType();
    }

    public boolean isValid(Number obj, ConstraintValidatorContext context) {
        boolean result = obj == null || doesEntityExists(obj);

        if (!result) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(entityType.toString() + ".doesnt_exists").addConstraintViolation();
        }

        return result;
    }

    private boolean doesEntityExists(Number key) {
        switch (entityType) {
            case Account:
                return getterService.accounts.getByKey(key).isPresent();
            case Place:
                return getterService.places.getByKey(key).isPresent();
            case ShopList:
                return getterService.shopLists.getByKey(key).isPresent();
            case ItemInstance:
                return getterService.itemInstances.getByKey(key).isPresent();
            case Container:
                return getterService.containers.getByKey(key).isPresent();
            case WishList:
                return getterService.wishLists.getByKey(key).isPresent();
            case WishListItem:
                return getterService.wishListItems.getByKey(key).isPresent();
            case Item:
                return getterService.items.getByKey(key).isPresent();
            case Category:
                return getterService.categories.getByKey(key).isPresent();
            default:
                throw new IllegalStateException("Wrong entity type: " + entityType);
        }
    }
}
