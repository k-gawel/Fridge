package org.california.model.validator.ItemFormValidatorTest;

import com.google.gson.Gson;
import org.california.model.transfer.request.ItemForm;
import org.california.model.validator.ItemFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class validate {


    @Test
    public void test() {
        Gson gson = new Gson();
        String json = "{\"name\":\"12332234324\",\"barcode\":345335353,\"placeId\":3,\"categoryId\":34,\"producer\":\"gfddg\",\"description\":\"ddddf\",\"errors\":null}";
        ItemForm itemForm = gson.fromJson(json, ItemForm.class);


        ItemFormValidator validator = new ItemFormValidator();

        boolean one = validator.validateName(itemForm.getName());
        boolean two = validator.validateDescription(itemForm.getDescription());
        boolean three = validator.validateProducent(itemForm.getProducent());
        boolean four = validator.validatePlaceId(itemForm.getPlaceId());
        boolean five = validator.validateStorage(itemForm.getStorage());

        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        System.out.println(four);
        System.out.println(five);

        System.out.println(validator.getMessagesAsString());
        assertTrue(validator.validate(itemForm));

    }

}