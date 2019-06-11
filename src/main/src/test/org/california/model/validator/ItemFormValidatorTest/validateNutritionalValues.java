package org.california.model.validator.ItemFormValidatorTest;

import org.california.model.validator.ItemFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class validateNutritionalValues {

    @Mock
    ItemFormValidator validator;

    @Before
    public void mock_validator() {
        when(validator.validateSingleString(contains("proper"), anyString(), anyInt(), anyInt())).thenReturn(true);
        when(validator.validateSingleString(contains("notValid"), anyString(), anyInt(), anyInt())).thenReturn(false);
        when(validator.validateSingleNutritionalValue(any())).thenCallRealMethod();
        doCallRealMethod().when(validator).validateNutritionalValues(any());
    }

    @Test
    public void all_values_are_not_valid() {

        Map<String, String> notValidValues = new HashMap<>();
        notValidValues.put("notValid1", "notValid2");
        notValidValues.put("notValid2", "proper1");
        notValidValues.put("proper", "notValid");

        assertEquals(3, notValidValues.size());
        validator.validateNutritionalValues(notValidValues);
        assertEquals(0, notValidValues.size());

    }


    @Test
    public void all_values_are_valid() {

        Map<String, String> validValues = new HashMap<>();
        validValues.put("proper1", "proper");
        validValues.put("proper2", "proper");

        assertEquals(validValues.size(), 2);
        validator.validateNutritionalValues(validValues);
        assertEquals(validValues.size(), 2);

    }


    @Test
    public void values_are_blank() {

        Map<String, String> blankValues = new HashMap<>();
        blankValues.put(null, "proper");
        blankValues.put("proper", null);

        validator.validateNutritionalValues(blankValues);
        assertTrue(blankValues.isEmpty());

    }

}