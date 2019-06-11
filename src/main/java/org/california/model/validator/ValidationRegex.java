package org.california.model.validator;

public enum ValidationRegex {

    ALPHANUMERIC("^[a-zA-Z0-9]+$"),
    EMAIL("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");

    private final String regex;

    ValidationRegex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public boolean check(String string) {

        boolean result;

        try {
            return string.matches(regex);
        } catch (Exception e) {
            return false;
        }

    }

}
