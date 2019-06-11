package org.california.model.transfer.request;

import java.io.Serializable;

public class AllergenForm implements Serializable {

    public String name;
    public boolean contains;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getContains() {
        return contains;
    }

    public void setContains(boolean contains) {
        this.contains = contains;
    }
}
