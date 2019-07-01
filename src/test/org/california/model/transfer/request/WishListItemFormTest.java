package org.california.model.transfer.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import lombok.ToString;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WishListItemFormTest {

    @Test
    public void test() throws IOException {

        String string = "{\n" +
                "  \"author_id\":\t10774,\n" +
                "  \"category_id\":\t2,\n" +
                "  \"comment\":\t\"My comment\",\n" +
                "  \"item_id\":\tnull,\n" +
                "  \"wish_list_id\":\t10781\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new ParanamerModule());

        WishListItemForm form = objectMapper.readerFor(WishListItemForm.class).readValue(string);

        System.out.println(form.toString());

    }

}