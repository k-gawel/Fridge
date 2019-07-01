package org.california.model.transfer.request.validator;

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

      if(!result) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(entityType.toString() + ".doesnt_exists").addConstraintViolation();
      }

      return result;
   }

   private boolean doesEntityExists(Number key) {
      switch (entityType) {
         case Account:
            return getterService.accounts.getByKey((long) key) != null;
         case Place:
            return getterService.places.getByKey(key) != null;
         case Container:
            return getterService.containers.getById((Long) key) != null;
         case WishList:
            return getterService.wishLists.getByKey(key) != null;
         case WishListItem:
            return getterService.wishListItems.getByKey(key) != null;
         case Item:
            return getterService.items.getByKey(key) != null;
         case Category:
            return getterService.categories.getByKey(key) != null;
         default:
            throw new IllegalStateException();
      }
   }
}
