package org.california.service.serialization.methodresolvers;

import com.github.wnameless.json.flattener.PrintMode;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Helper {

    boolean isNumberArray(String string) {
        final String regex = "(\\d+,{0,})*";
        return string.matches("\\[" + regex + "]") || string.matches(regex);
    }

    @Nullable
    Collection<Number> toNumbersArray(String string) {
        if(string == null) return null;

        if(string.matches("\\[.*]"))
            string = string.substring(1, string.length() - 2);

        String[] numberStrings = string.split(",");
        return Arrays.stream(numberStrings).map(this::getNumber).collect(Collectors.toList());
    }


    Map<String, Object> map(Map<String, String[]> map) {
        var result = new HashMap<String, Object>();
        for (var entry : map.entrySet()) {
            String key = entry.getKey();
            Object val = getValue(entry.getValue());
            result.put(key, val);
        }
        return result;
    }

    Object getValue(String[] strings) {
        try {
            return getValue(strings[0]);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    Object getValue(String string) {
        Boolean b = getBoolean(string);
        Number  n = getNumber(string);
        if(StringUtils.isBlank(string) || string.equals("null"))
            return null;
        else if(b != null)
            return b;
        else if(n != null)
            return n;
        else
            return string;
    }

    Long getNumber(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    Boolean getBoolean(String string) {
        switch (string) {
            case "true":
                return true;
            case "false":
                return false;
            default:
                return null;
        }
    }

    String parametersToJson(Map<String, String[]> parameters) {
        var map = map(parameters);
        var flatJson = new Gson().toJson(map);
        var unflattener = new JsonUnflattener(flatJson);
        unflattener.withPrintMode(PrintMode.PRETTY);
        return unflattener.unflatten();
    }


}
