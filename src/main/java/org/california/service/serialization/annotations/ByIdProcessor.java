package org.california.service.serialization.annotations;

import org.california.model.entity.BaseEntity;
import org.california.service.getter.BaseGetter;
import org.california.service.getter.GetterService;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

public class ByIdProcessor {

    private final Class<? extends BaseEntity> type;
    private final Number id;

    public ByIdProcessor(@Nullable Number id, @NotNull Field field) {
        if(!field.isAnnotationPresent(ById.class))
            throw new IllegalArgumentException("Field " + field + " isn't annotated with @ById.");
        if(!BaseEntity.class.isAssignableFrom(field.getType()))
            throw new IllegalArgumentException(field.getType() + " isn't assignable with @ById");

        this.type  = (Class<? extends BaseEntity>) field.getType();
        this.id    = id;
    }


    public ByIdProcessor(@Nullable Number id, @NotNull Parameter parameter) {
        if(!parameter.isAnnotationPresent(ById.class))
            throw new IllegalArgumentException("Parameter " + parameter.toString() + " isn't annotated with @ById.");
        if(!BaseEntity.class.isAssignableFrom(parameter.getType()))
            throw new IllegalArgumentException(parameter.getType() + " isn't assignable with @ById");

        this.type = (Class<? extends BaseEntity>) parameter.getType();
        this.id   = id;
    }


    @Nullable
    public BaseEntity getValue() {
        BaseGetter<? extends BaseEntity> getter = getGetter();
        return getter.getByKey(id).orElse(null);
    }


    private BaseGetter<? extends BaseEntity> getGetter() {
        BaseGetter<? extends BaseEntity> getter = GetterService.GETTER.get(type);
        if (getter == null)
            throw new IllegalStateException("There is no available getter for " + type);

        return getter;
    }


}
