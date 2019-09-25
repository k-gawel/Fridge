package org.california.service.serialization;

import org.junit.Test;

public class ConstructorGetterTest {

    private static class ProperForm {
        private final String name;
        private final int xd;


        private ProperForm(String name, int xd) {
            this.name = name;
            this.xd = xd;
        }
    }

    @Test
    public void simpleTest() {
        ConstructorGetter getter = new ConstructorGetter<>(ProperForm.class);
    }

}