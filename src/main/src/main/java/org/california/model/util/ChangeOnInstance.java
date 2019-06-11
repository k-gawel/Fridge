package org.california.model.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChangeOnInstance {
    ADD, DELETE, OPEN, FROZE, UNFROZE;

    @JsonValue
    public int toValue() {
        return ordinal();
    }

}
