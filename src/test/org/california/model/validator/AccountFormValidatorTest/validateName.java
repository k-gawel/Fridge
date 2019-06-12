package org.california.model.validator.AccountFormValidatorTest;


import org.apache.commons.collections.CollectionUtils;
import org.california.model.validator.AccountFormValidator;
import org.california.model.validator.ValidationRegex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidationRegex.class)
public class validateName {

    AccountFormValidator validator;


    @Test
    public void not_alphanumeric_name() {

        validator = new AccountFormValidator();
        assertFalse(validator.validateName("___12s_f;;["));
        assertTrue(validator.getMessages().contains("name.not_alphanumeric"));

    }

    @Test
    public void alphanumeric_name() {

        validator = new AccountFormValidator();
        validator.validateName("alphanumericaf");
        assertFalse(validator.getMessages().contains("name.not_alphanumeric"));

    }


    @Test
    public void alphanumeric_but_not_valid_name() {

        String tooShortName = "12";
        String tooLongName = "012345678901234567891";

        validator = new AccountFormValidator();
        assertFalse(validator.validateName(tooShortName));
        System.out.println(validator.getMessagesAsString());
        assertTrue(validator.getMessages().contains("name.too_short"));



        validator = new AccountFormValidator();
        assertFalse(validator.validateName(tooLongName));
        assertTrue(validator.getMessages().contains("name.too_long"));

    }


    @Test
    public void proper_name() {
        String alphanumericAnd12CharactersLongName = "simpleName12";

        validator = new AccountFormValidator();
        assertTrue(validator.validateName(alphanumericAnd12CharactersLongName));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }

}