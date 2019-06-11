package org.california.model.validator.ItemFormValidatorTest;

import org.apache.commons.collections.ListUtils;
import org.california.model.validator.ItemFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class validateAllergen {

    @Mock
    ItemFormValidator validator;

    @Before
    public void mock_validator() {
        when(validator.validateSingleAllergen(contains("proper"))).thenReturn(true);
        when(validator.validateSingleAllergen(contains("notvalid"))).thenReturn(false);
        doCallRealMethod().when(validator).validateAllergens(any());
    }

    @Test
    public void blank_collection() {

        List<String> nullList = null;
        validator.validateAllergens(nullList);
        assertNull(nullList);

        List<String> emptyList = new ArrayList<>();
        validator.validateAllergens(emptyList);
        assertTrue(emptyList.isEmpty());

    }


    @Test
    public void all_elements_not_valid() {

        List<String> notValidList = new ArrayList<>(Arrays.asList("notvalid1", "notvalid2", "notvalid3"));
        assertFalse(notValidList.isEmpty());
        validator.validateAllergens(notValidList);
        assertTrue(notValidList.isEmpty());

    }


    @Test
    public void few_elements_not_valid() {

        int numberOfNotValidElements = 3;
        int numberOfValidElements = 2;

        List<String> notValidList = Arrays.asList("notvalid1", "notvalid2", "notvalid3");
        List<String> validList = Arrays.asList("proper1", "proper2");

        List<String> allElements = ListUtils.union(notValidList, validList);
        assertEquals(allElements.size(), numberOfNotValidElements + numberOfValidElements);

        validator.validateAllergens(allElements);
        assertEquals(allElements.size(), numberOfValidElements);
    }


    @Test
    public void all_elements_are_valid() {

        int numberOfValidElements = 2;
        List<String> validList = Arrays.asList("proper1", "proper2");
        assertEquals(validList.size(), numberOfValidElements);
        validator.validateAllergens(validList);
        assertEquals(validList.size(), numberOfValidElements);


    }


}