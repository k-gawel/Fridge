package org.california.model.validator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Validator {

    private Set<String> messages = new HashSet<>();


    void setMessage(String message) {
        messages.add(message);
    }


    public Set<String> getMessages() {
        return messages;
    }


    String normalizeSpaces(String string) {

        try {
            return string.trim().replaceAll(" +", " ");
        } catch (NullPointerException e) {
            return null;
        }

    }


    boolean validateSingleString(String string, String name) {
        return validateSingleString(string, name, 3, 25);
    }


    public boolean validateId(Serializable id, String name) {
        if(id == null) {
            setMessage(name + ".empty");
            return false;
        }

        return true;
    }


    public boolean validateSingleString(String string, String name, int minLength, int maxLength) {

        string = normalizeSpaces(string);

        if(StringUtils.isBlank(string)) {
            setMessage(name + ".blank");
            return false;
        } else if(string.length() < minLength) {
            setMessage(name + ".too_short");
            return false;
        } else if(string.length() > maxLength) {
            setMessage(name + ".too_long");
            return false;
        } else
            return true;

    }


    public <T> boolean validate(T object) {
        if(object == null) {
            setMessage("object.null");
            return false;
        }

        boolean result = true;

        Collection<Method> methods = getValidationMethodsForObject(object);

        Method getter;
        boolean validationResult = true;

        for(Method validateMethod: methods) {

            try {
                getter = object.getClass().getMethod(validateMethod.getName().replace("validate", "get"));

                Object getterResult = getter.invoke(object);

                if(validateMethod.getReturnType() == boolean.class)
                    validationResult = (boolean) validateMethod.invoke(this, getterResult);
                else
                    validateMethod.invoke(this, getterResult);

                if(!validationResult)
                    result = false;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return result;
    }


    private <T> Collection<Method> getGetters(T object) {
        return Arrays.stream(object.getClass().getMethods())
                .filter(m -> m.getName().startsWith("get"))
                .collect(Collectors.toList());
    }


    private Collection<String> getFieldNamesFromGetters(Collection<Method> getters) {

        return getters.stream()
                .map(g -> g.getName().replaceAll("get", ""))
                .collect(Collectors.toList());
    }


    private Collection<String> getValidationMethodsNames(Collection<String> fieldNames) {

        return fieldNames.stream()
                .map(n -> "validate" + n)
                .collect(Collectors.toList());
    }


    private Collection<Method> getValidationMethods(Collection<String> names) {

        return Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(m -> names.contains(m.getName()))
                .collect(Collectors.toList());
    }


    private <T> Collection<Method> getValidationMethodsForObject(T object) {

        Collection<Method> getters = getGetters(object);
        Collection<String> fieldNames = getFieldNamesFromGetters(getters);
        Collection<String> validationMethodsNames = getValidationMethodsNames(fieldNames);

        return getValidationMethods(validationMethodsNames);
    }


    public String getMessagesAsString() {
        if(CollectionUtils.isEmpty(messages))
            return "";
        return messages.stream().collect(Collectors.joining("&"));
    }

}
