package org.california.model.transfer.request.validator;


import java.lang.annotation.*;
import javax.validation.*;

@Constraint(validatedBy = EntityExistsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface EntityExists {

    String message() default "entity.doesnt_exists";

    EntityType entityType();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};

    enum EntityType {
        Account("account"), Place("place"), Container("container"), WishList("wishlist"), WishListItem("wishlistitem"), Item("item"), Category("category");

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