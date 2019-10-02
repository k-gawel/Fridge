package org.california.model.transfer.request.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EntityExistsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EntityExists {

    String message() default "entity.doesnt_exists";

    EntityType entityType();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    enum EntityType {
        Account("account"), Place("place"),
        Container("container"), ShopList("shopList"),
        WishList("wishlist"), WishListItem("wishlistitem"),
        Item("item"), ItemInstance("itemInstance"),
        Category("category");

        private final String stringType;

        EntityType(String type) {
            this.stringType = type;
        }

        @Override
        public String toString() {
            return this.stringType;
        }
    }

}