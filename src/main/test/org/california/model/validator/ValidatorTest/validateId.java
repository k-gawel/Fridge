package org.california.model.validator.ValidatorTest;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.validator.Validator;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validateId {

    Validator validator;

    @Test
    public void null_id() {

        validator = new Validator();
        Serializable id = null;
        assertFalse(validator.validateId(id, "name"));
        assertTrue(validator.getMessages().contains("name.empty"));

    }

    @Test
    public void proper_id() {

        validator = new Validator();
        Serializable id = 1L;
        assertTrue(validator.validateId(id, "name"));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }

}