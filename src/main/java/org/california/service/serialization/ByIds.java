package org.california.service.serialization;

import org.california.model.entity.BaseEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ByIds {

    Class<? extends BaseEntity> entity();
    boolean failOnNull() default false;

}
