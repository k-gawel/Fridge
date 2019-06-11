package org.california.model.validator.WishListItemFormValidatorTest;

import org.california.model.transfer.request.WishListItemForm;
import org.california.model.validator.WishListItemFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class validate {

    @Mock
    WishListItemFormValidator validator;

    @Before
    public void mock_validator() {
        when(validator.validateComment(any())).thenReturn(true);
        when(validator.validateAuthorId(any())).thenReturn(true);
        when(validator.validateWishListId(any())).thenReturn(true);
        when(validator.validateItemId(any())).thenCallRealMethod();
        when(validator.validateCategoryId(any())).thenCallRealMethod();
        when(validator.validate(any())).thenCallRealMethod();
    }


    @Test
    public void both_categoryid_and_itemid_null() {

        WishListItemForm form = new WishListItemForm();
        assertFalse(validator.validate(form));

    }

    @Test
    public void both_categoryid_and_itemid_not_null() {

        WishListItemForm form = new WishListItemForm();
        form.setItemId(1L);
        form.setCategoryId(1L);

        boolean one = validator.validateAuthorId(form.getAuthorId());
        boolean two = validator.validateComment(form.getComment());
        boolean three = validator.validateWishListId(form.getItemId());
        boolean four = validator.validateCategoryId(form.getCategoryId());
        boolean five = validator.validateItemId(form.getItemId());

        assertFalse(validator.validate(form));

    }

    @Test
    public void proper_values() {

        WishListItemForm form;


        form = new WishListItemForm();
        form.setItemId(1L);

        assertTrue(validator.validate(form));

        form = new WishListItemForm();
        form.setCategoryId(1L);
        assertFalse(validator.validate(form));

    }

}