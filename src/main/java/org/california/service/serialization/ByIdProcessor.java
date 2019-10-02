package org.california.service.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import org.california.model.entity.BaseEntity;
import org.california.service.getter.BaseGetter;
import org.california.service.getter.GetterService;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

public class ByIdProcessor {

    private final Field  field;
    private final Number id;

    public ByIdProcessor(@Nullable JsonNode node, @NotNull Field field) {
        if(field.getAnnotation(ById.class) == null)
            throw new IllegalArgumentException("Field " + field + " isn't annotated with @ById.");

        this.field = field;
        this.id    = node != null ? node.numberValue() : null;
    }


    public BaseEntity getValue() {
        BaseGetter<? extends BaseEntity> getter = getGetter();
        return getter.getByKey(id).orElse(null);
    }


    private BaseGetter<? extends BaseEntity> getGetter() {
        Class<?> fieldType = field.getType();
        BaseGetter<? extends BaseEntity> getter = GetterService.GETTER.get(fieldType);
        if (getter == null)
            throw new IllegalStateException("There is no available getter for " + fieldType);

        return getter;
    }


}
