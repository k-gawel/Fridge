package org.california.service.serialization;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructorGetter<T> {

    private final Class<T> clazz;
    private final Constructor<T> constructor;

    public ConstructorGetter(Class<T> clazz) {
        this.clazz = clazz;
        this.constructor = getProperConstructor();
    }

    private Constructor<T> getProperConstructor() {
        Class<?>[] types = new Class<?>[clazz.getDeclaredFields().length];
        List<Class<?>> typesList = Arrays.stream(clazz.getDeclaredFields()).map(Field::getType).collect(Collectors.toList());

        try {
            return clazz.getDeclaredConstructor(typesList.toArray(types));
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("There is no proper constructor for " + clazz);
        }
    }


    public Constructor<T> getConstructor() {
        return constructor;
    }


    private boolean areAllFieldsFinal() {
        return Arrays.stream(clazz.getDeclaredFields()).map(Field::getModifiers).allMatch(Modifier::isFinal);
    }

    private boolean areSomeFieldsFinal() {
        return Arrays.stream(clazz.getDeclaredFields()).map(Field::getModifiers).anyMatch(Modifier::isFinal);
    }





}
